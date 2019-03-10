package com.azeroth.bwl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.azeroth.model.CompanyDateTripBean;
import com.azeroth.model.OutWorkBean;
import com.azeroth.utility.API;
import com.azeroth.utility.SoapRequestMessage;
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
        SoapRequestMessage messageDaka=new SoapRequestMessage(API.KQ.BassAdress);
        messageDaka.action=API.KQ.Action.GETSCHEDULBYUSERID;
        message.parameter.put("UserID", BwApplication.appInstance.userInfo.Id);
//        message.parameter.put("longitude", longitude);
//        message.parameter.put("latitude", latitude);
        this.SendSoapRequest(messageDaka,this::dakaHandler);
    }

    void dakaHandler(SoapObject result){

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
