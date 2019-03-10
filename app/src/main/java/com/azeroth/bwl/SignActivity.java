package com.azeroth.bwl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.azeroth.model.CompanyDateTripBean;
import com.azeroth.model.OutWorkBean;
import com.azeroth.utility.API;
import com.azeroth.utility.BwLocationListener;
import com.azeroth.utility.SoapRequestMessage;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

public class SignActivity extends BwActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void initView() throws Exception {
        setContentView(R.layout.activity_sign);
    }

    @Override
    public void initData() throws Exception {
        //外勤列表
        SoapRequestMessage message=new SoapRequestMessage(API.KQ.BassAdress);
        message.action=API.KQ.Action.GETTODAYOUTSIDEAPPLY;
        message.parameter.put("UserID",BwApplication.appInstance.userInfo.Id);
        this.SendSoapRequest(message,this::outWorkHandler);
        //最早到的人
        SoapRequestMessage messageFirstArrive=new SoapRequestMessage(API.KQ.BassAdress);
        messageFirstArrive.action=API.KQ.Action.GETFIRSTARRIVE;
        this.SendSoapRequest(messageFirstArrive,this::firstArriveHandler);
        //获取打卡信息
        initDaka();

    }

    void initDaka() throws Exception {
        boolean initdaka=false;
        LocationClient mLocationClient=new LocationClient(this);
        BwLocationListener locationListener=new BwLocationListener();
        locationListener.handlerReceiveLocation=this::initDaka;
        mLocationClient.registerLocationListener(locationListener);
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，设置定位模式，默认高精度
//LocationMode.Hight_Accuracy：高精度；
//LocationMode. Battery_Saving：低功耗；
//LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("bd09ll");
//可选，设置返回经纬度坐标类型，默认GCJ02
//GCJ02：国测局坐标；
//BD09ll：百度经纬度坐标；
//BD09：百度墨卡托坐标；
//海外地区定位，无需设置坐标类型，统一返回WGS84类型坐标

        option.setScanSpan(1000);
//可选，设置发起定位请求的间隔，int类型，单位ms
//如果设置为0，则代表单次定位，即仅定位一次，默认为0
//如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
//可选，设置是否使用gps，默认false
//使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
//可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
//可选，定位SDK内部是一个service，并放到了独立进程。
//设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
//可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5*60*1000);
//可选，V7.2版本新增能力
//如果设置了该接口，首次启动定位时，会先判断当前Wi-Fi是否超出有效期，若超出有效期，会先重新扫描Wi-Fi，然后定位

        option.setEnableSimulateGps(false);
//可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        mLocationClient.setLocOption(option);
//mLocationClient为第二步初始化过的LocationClient对象
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        mLocationClient.start();
    }

    void initDaka(BDLocation location, Integer tickTimes)  {
        if(tickTimes>1)
            return;
        double latitude = location.getLatitude();    //获取纬度信息
        Toast.makeText(SignActivity.this,String.valueOf(latitude),Toast.LENGTH_LONG).show();
        double longitude = location.getLongitude();    //获取经度信息
        Toast.makeText(SignActivity.this,String.valueOf(longitude),Toast.LENGTH_LONG).show();
        float radius = location.getRadius();    //获取定位精度，默认值为0.0f
        String coorType = location.getCoorType();
        //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
        int errorCode = location.getLocType();
        //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
        SoapRequestMessage messageDaka=new SoapRequestMessage(API.KQ.BassAdress);
        messageDaka.action=API.KQ.Action.GETSCHEDULBYUSERID;
        messageDaka.parameter.put("UserID", BwApplication.appInstance.userInfo.Id);
        messageDaka.parameter.put("longitude", longitude+"");
        messageDaka.parameter.put("latitude", latitude+"");
        this.wrapperRunnable(()->this.SendSoapRequest(messageDaka,this::dakaHandler)).run();
    }

    void dakaHandler(SoapObject result) throws Exception {
        SoapObject provinceSoapObject = (SoapObject) result.getProperty(API.KQ.Action.GETFIRSTARRIVE + "Result");
        String json = provinceSoapObject.getProperty(0).toString();
        String json2 = provinceSoapObject.getProperty(1).toString();

        JSONArray array = new JSONArray(json2);
        JSONArray array1 = array.getJSONObject(0).getJSONArray("content");
        String SignType = array1.getJSONObject(0).getString("SignType");
        String id = array1.getJSONObject(0).getString("ID");
        String AttendLocationID = array1.getJSONObject(0).getString("AttendLocationID");
        TextView tv_signintime= (TextView)this.findViewById(R.id.tv_signintime);
        if (array1.getJSONObject(0).getString("SignInTiem").equals("")) {
            tv_signintime.setText("");
        } else {
            String time = array1.getJSONObject(0).getString("SignInTiem");
            String signintime = time.substring(time.indexOf(" "), time.length());
            tv_signintime.setText(signintime);
        }
        TextView tv_signouttime= (TextView)this.findViewById(R.id.tv_signouttime);
        if (array1.getJSONObject(0).getString("SignOutTime").equals("")) {
            tv_signouttime.setText("");
        } else {
            String time = array1.getJSONObject(0).getString("SignOutTime");
            String signouttime = time.substring(time.indexOf(" "), time.length());
            tv_signouttime.setText(signouttime);
        }
    }

    void  firstArriveHandler(SoapObject result) throws  Exception{
        SoapObject provinceSoapObject = (SoapObject) result.getProperty(API.KQ.Action.GETFIRSTARRIVE + "Result");
        String json = provinceSoapObject.getProperty(0).toString();
        String json2 = provinceSoapObject.getProperty(1).toString();
        JSONObject jsonObject = new JSONArray(json2).getJSONObject(0).getJSONArray("content").getJSONObject(0);
        ImageView iv_head=this.findViewById(R.id.iv_head);
        Glide.with(this).load(jsonObject.getString("headimage")).into(iv_head);
        TextView tv_name=this.findViewById(R.id.tv_name);
        tv_name.setText(jsonObject.getString("TrueName"));
        TextView tv_time=this.findViewById(R.id.tv_time);
        tv_time.setText(jsonObject.getString("SignInTiem"));
        TextView tv_time1=this.findViewById(R.id.tv_time1);
        tv_time1.setVisibility(View.VISIBLE);
        TextView tv_time2=this.findViewById(R.id.tv_time2);
        tv_time2.setVisibility(View.VISIBLE);
    }

    void  outWorkHandler(SoapObject result) throws JSONException {
        SoapObject provinceSoapObject = (SoapObject) result.getProperty(API.KQ.Action.GETTODAYOUTSIDEAPPLY + "Result");
        String json = provinceSoapObject.getProperty(0).toString();
        String json2 = provinceSoapObject.getProperty(1).toString();
        String rt = new JSONArray(json2).getJSONObject(0).getJSONArray("content").toString();
        ArrayList<OutWorkBean> lstOutWork = com.alibaba.fastjson.JSON.parseObject(json2,  new TypeReference<ArrayList<OutWorkBean>>() {});
        BwListAdapter<OutWorkBean> adapter=new BwListAdapter<>(this,lstOutWork);
        adapter.createViewHandler=this::createOutworkItem;
        ListView lv= (ListView)this.findViewById(R.id.outwork_listview);
        lv.setAdapter(adapter);
    }

    View createOutworkItem(BwActivity context, List<OutWorkBean> lstValue, Integer position, Object tag){
        View view =View.inflate(this,R.layout.activity_sign_outwork_item,null);
       TextView tv_date = (TextView) view.findViewById(R.id.tv_date);
        TextView tv_address = (TextView) view.findViewById(R.id.tv_address);
        TextView tv_explain = (TextView) view.findViewById(R.id.tv_explain);
        OutWorkBean outWorkBean = lstValue.get(position);
        String starttime = outWorkBean.getStartTime();
        String endtime = outWorkBean.getEndTime();
        tv_date.setText(starttime.substring(starttime.indexOf(" ")+1,starttime.length())+" - "+endtime.substring(endtime.indexOf(" ")+1,endtime.length()));
        tv_address.setText(outWorkBean.getAddress());
        tv_explain.setText("外勤说明："+outWorkBean.getReason());
        return view;
    }
}
