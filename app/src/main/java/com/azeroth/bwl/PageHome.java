package com.azeroth.bwl;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.azeroth.model.CompanyDateTripBean;
import com.azeroth.model.JMessageBean;
import com.azeroth.model.QianDaoBean;
import com.azeroth.model.TaskListBean;
import com.azeroth.model.UserAllBean;
import com.azeroth.utility.API;
import com.azeroth.utility.SoapRequestMessage;
import com.bumptech.glide.Glide;

import org.json.JSONObject;
import org.ksoap2.serialization.SoapObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PageHome extends Page {
    GridView gvMenu;
    public PageHome(BwActivity activity){
        super(activity,View.inflate(activity,R.layout.page_home,null));
    }
    @Override
    public void initView() throws Exception{
        this.gvMenu=(GridView)this.view.findViewById(R.id.gv_content);
        //this.gvMenu.setOnItemClickListener(this::gvMenuOnItemClick);
        //签到
        this.view.findViewById(R.id.ll_qiandao).setOnClickListener(this.hostActivity.wrapperOnclickListener(x->{
            Intent it=new Intent();
            it.setClass(this.hostActivity,SignActivity.class);
            this.hostActivity.startActivity(it);
        }));
    }
    @Override
    public void initData() throws Exception {
        Date dateTimeNow = new Date();
        ((TextView)this.view.findViewById(R.id.home_tv_day)).setText(new SimpleDateFormat("dd").format(dateTimeNow));
        ((TextView)this.view.findViewById(R.id.home_tv_month)).setText(new SimpleDateFormat("yyyy.MM").format(dateTimeNow));
        ((TextView)this.view.findViewById(R.id.home_tv_week)).setText(new SimpleDateFormat("EEEE").format(dateTimeNow));
        //获取任务
        SoapRequestMessage message=new SoapRequestMessage(API.ERPTask.BassAdress);
        message.action=API.ERPTask.Action.getMyTaskListForApp;
        message.parameter.put("UR_TR_UNION_ID",BwApplication.appInstance.userInfo.Tid);
        this.hostActivity.SendSoapRequest(message,this::handlerTaskInfo);
        //获取最新提醒
        SoapRequestMessage tipMessage=new SoapRequestMessage(API.ERP.BassAdrees);
        tipMessage.action=API.ERP.Action.JPushGetJMessageLogIndex;
        tipMessage.parameter.put("UnionID",BwApplication.appInstance.userInfo.Tid);
        tipMessage.parameter.put("AppType","2");
        this.hostActivity.SendSoapRequest(tipMessage,this::handlerTipInfo);
        //签到前三
        SoapRequestMessage kqMessage=new SoapRequestMessage(API.KQ.BassAdress);
        kqMessage.action=API.KQ.Action.getRankingsTop3;
        kqMessage.parameter.put("type","1");
        this.hostActivity.SendSoapRequest(kqMessage,this::handlerKqTopSignin);
        //map.put("type", type);
        //签退前三
        SoapRequestMessage kqMessageOut=new SoapRequestMessage(API.KQ.BassAdress);
        kqMessageOut.action=API.KQ.Action.getRankingsTop3;
        kqMessageOut.parameter.put("type","2");
        this.hostActivity.SendSoapRequest(kqMessageOut,this::handlerKqTopSignout);
        //移动办公
        SoapRequestMessage menuMessage=new SoapRequestMessage(API.ERP.BassAdrees);
        menuMessage.action=API.ERP.Action.GetUserHomeMenu;
        //map.put("UNION_ID", UNION_ID);//评论对象的ID
        menuMessage.parameter.put("UNION_ID",BwApplication.appInstance.userInfo.Tid);
        this.hostActivity.SendSoapRequest(menuMessage,this::handlerMenu);
        //当月课程安排
        SoapRequestMessage kechengMessage=new SoapRequestMessage(API.ERP.BassAdrees);
        kechengMessage.action=API.ERP.Action.GETCOMPANYTRIPOFFULLPICTURE;
        kechengMessage.parameter.put("Time",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        this.hostActivity.SendSoapRequest(kechengMessage,this::handlerKecheng);
    }

    void  gvMenuOnItemClick(AdapterView<?> parent, View view, int position, long id){
        UserAllBean.PrivilegeBean model= (UserAllBean.PrivilegeBean)view.getTag();
        Toast.makeText(this.hostActivity,com.alibaba.fastjson.JSON.toJSONString(model),Toast.LENGTH_SHORT).show();
    }

    void handlerKecheng(String result,String json2){
        ArrayList<CompanyDateTripBean> lstKecheng = com.alibaba.fastjson.JSON.parseObject(json2,  new TypeReference<ArrayList<CompanyDateTripBean>>() {});
        BwListAdapter<CompanyDateTripBean> adapter=new BwListAdapter<>(this.hostActivity,lstKecheng);
        adapter.createViewHandler=this::createKechengItemView;
        ListView lv= (ListView)this.view.findViewById(R.id.lv_kecheng);
        lv.setAdapter(adapter);
    }

    View createKechengItemView(BwActivity context, List<CompanyDateTripBean> lstValue, int position,Object tag){
        View view=View.inflate(this.hostActivity,R.layout.page_home_kecheng_item,null);
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        TextView tvDate = (TextView) view.findViewById(R.id.tv_date);
        tvName.setText(lstValue.get(position).getYS_DESC());
        tvDate.setText(lstValue.get(position).getStart() + "至" + lstValue.get(position).getEnd());
        return view;
    }


    void handlerMenu(String result,String json2) throws Exception{
        String userSelect = new JSONObject(json2).optString("UserSelect");
        String UserAll = new JSONObject(json2).optString("UserAll");
        ArrayList<UserAllBean> lstMenu = com.alibaba.fastjson.JSON.parseObject(userSelect,  new TypeReference<ArrayList<UserAllBean>>() {});
        BwListAdapter<UserAllBean.PrivilegeBean> adapter=new BwListAdapter<>(this.hostActivity,lstMenu.get(0).getPrivilege());
        adapter.createViewHandler=this::createMenuItemView;
        this.gvMenu.setAdapter(adapter);
    }

    View createMenuItemView(BwActivity context, List<UserAllBean.PrivilegeBean> lstValue, int position,Object tag){
        View view=View.inflate(context,R.layout.page_home_menu_item,null);
        ImageView  ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        tvName.setText(lstValue.get(position).getNAME());
        Glide.with(context).load(lstValue.get(position).getICON()).into(ivIcon);
        view.setTag(lstValue.get(position));
//        RelativeLayout  mainview = (RelativeLayout) view.findViewById(R.id.ll_menu_item);
//        AbsListView.LayoutParams params = new AbsListView.LayoutParams((ScreenUtils.getScreenWidth(view.getContext()) - DensityUtils.dp2px(mContext, 2)) / 4, ScreenUtils.getScreenWidth(view.getContext()) / 4);
        //mainview.setLayoutParams(params);
        return view;
    }

    void handlerKqTopSignout(String result,String json2){
        ArrayList<QianDaoBean> qianDaoBeen = com.alibaba.fastjson.JSON.parseObject(json2,  new TypeReference<ArrayList<QianDaoBean>>() {});
        //this.view.findViewById(R.id.ll_no_late).setVisibility(View.VISIBLE);
        Glide.with(this.hostActivity).load(qianDaoBeen.get(0).getContent().get(0).getHeadimage()).into((ImageView)view.findViewById(R.id.iv_go_first));
        ((TextView)view.findViewById(R.id.tv_name_go_first)).setText(qianDaoBeen.get(0).getContent().get(0).getTrueName());
        ((TextView)view.findViewById(R.id.tv_go_time1)).setText(qianDaoBeen.get(0).getContent().get(0).getSignInTiem());

        Glide.with(this.hostActivity).load(qianDaoBeen.get(0).getContent().get(1).getHeadimage()).into((ImageView)view.findViewById(R.id.iv_go_second));
        ((TextView)view.findViewById(R.id.tv_name_go_second)).setText(qianDaoBeen.get(0).getContent().get(1).getTrueName());
        ((TextView)view.findViewById(R.id.tv_go_time2)).setText(qianDaoBeen.get(0).getContent().get(1).getSignInTiem());

        Glide.with(this.hostActivity).load(qianDaoBeen.get(0).getContent().get(2).getHeadimage()).into((ImageView)view.findViewById(R.id.iv_go_third));
        ((TextView)view.findViewById(R.id.tv_name_go_third)).setText(qianDaoBeen.get(0).getContent().get(2).getTrueName());
        ((TextView)view.findViewById(R.id.tv_go_time3)).setText(qianDaoBeen.get(0).getContent().get(2).getSignInTiem());
    }

    void handlerKqTopSignin(String result,String json2){
        ArrayList<QianDaoBean> qianDaoBeen = com.alibaba.fastjson.JSON.parseObject(json2,  new TypeReference<ArrayList<QianDaoBean>>() {});
        //this.view.findViewById(R.id.ll_no_first).setVisibility(View.VISIBLE);
        Glide.with(this.hostActivity).load(qianDaoBeen.get(0).getContent().get(0).getHeadimage()).into((ImageView)view.findViewById(R.id.iv_come_first));
        ((TextView)view.findViewById(R.id.tv_name_come_first)).setText(qianDaoBeen.get(0).getContent().get(0).getTrueName());
        ((TextView)view.findViewById(R.id.tv_time1)).setText(qianDaoBeen.get(0).getContent().get(0).getSignInTiem());

        Glide.with(this.hostActivity).load(qianDaoBeen.get(0).getContent().get(1).getHeadimage()).into((ImageView)view.findViewById(R.id.iv_come_second));
        ((TextView)view.findViewById(R.id.tv_name_come_second)).setText(qianDaoBeen.get(0).getContent().get(1).getTrueName());
        ((TextView)view.findViewById(R.id.tv_time2)).setText(qianDaoBeen.get(0).getContent().get(1).getSignInTiem());

        Glide.with(this.hostActivity).load(qianDaoBeen.get(0).getContent().get(2).getHeadimage()).into((ImageView)view.findViewById(R.id.iv_come_third));
        ((TextView)view.findViewById(R.id.tv_name_come_third)).setText(qianDaoBeen.get(0).getContent().get(2).getTrueName());
        ((TextView)view.findViewById(R.id.tv_time3)).setText(qianDaoBeen.get(0).getContent().get(2).getSignInTiem());
    }

    void handlerTipInfo(String result,String json2){
        ArrayList<JMessageBean> lstRT=
                com.alibaba.fastjson.JSON.parseObject(json2,new TypeReference<ArrayList<JMessageBean>>() {});
        BwListAdapter<JMessageBean> adapter=new BwListAdapter<>(this.hostActivity,lstRT);
        adapter.createViewHandler=this::createTipItemView;
        ListView lvTip= (ListView)this.view.findViewById(R.id.home_lv_tip);
        lvTip.setAdapter(adapter);
    }



    View createTipItemView(BwActivity context, List<JMessageBean> lstValue, int position, Object tag){
        View view=View.inflate(context,R.layout.page_home_tip_item,null);
        ImageView  ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvDate = (TextView) view.findViewById(R.id.tv_date);
        JMessageBean model=lstValue.get(position);
        tvTitle.setText(model.getTitle());
        tvDate.setText(model.getNewTime());
        Glide.with(this.hostActivity).load(model.getHeadImg()).into(ivIcon);
        return view;
    }

    void handlerTaskInfo(String result,String json2){
        ArrayList<TaskListBean> lstRT=
                com.alibaba.fastjson.JSON.parseObject(json2,new TypeReference<ArrayList<TaskListBean>>() {});
        TaskListBean taskWrapper=lstRT.get(0);
        BwListAdapter<TaskListBean.DataBean> adapter=new BwListAdapter<>(this.hostActivity,taskWrapper.getData(),taskWrapper);
        adapter.createViewHandler=this::createTaskItemView;
        ListView lvTask= (ListView)this.view.findViewById(R.id.home_lv_task);
        lvTask.setAdapter(adapter);
    }

    View createTaskItemView(BwActivity context, List<TaskListBean.DataBean> lstValue, int position, Object tag){
        TaskListBean taskDateBeen=(TaskListBean)tag;
        View view=View.inflate(context,R.layout.page_home_task_item,null);
        TextView tvFlag = (TextView) view.findViewById(R.id.tv_flag);
        ImageView ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        TextView  tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvReceveName = (TextView) view.findViewById(R.id.tv_receve_name);
        TextView tvDate = (TextView) view.findViewById(R.id.tv_date);
        TextView  tvJoinName = (TextView) view.findViewById(R.id.tv_join_name);
        TextView  tvLev = (TextView) view.findViewById(R.id.tv_lev);
        TextView tvState = (TextView) view.findViewById(R.id.tv_state);
        tvDate.setText(taskDateBeen.getData().get(position % 10).getStartTime().substring(5)
                + "—" + taskDateBeen.getData().get(position % 10).getEndTime().substring(5));
        tvTitle.setText(taskDateBeen.getData().get(position % 10).getName());
        tvReceveName.setText("接收人：" + taskDateBeen.getData().get(position % 10).getReceiveName());
        tvLev.setText(taskDateBeen.getData().get(position % 10).getEventName());
        tvLev.setTextColor(Color.parseColor(taskDateBeen.getData().get(position % 10).getEventColor()));
        tvState.setText(taskDateBeen.getData().get(position % 10).getStatusName());
        tvState.setTextColor(Color.parseColor(taskDateBeen.getData().get(position % 10).getStatusColor()));
        tvFlag.setText(taskDateBeen.getData().get(position % 10).getOrderLevels());
//        GradientDrawable drawable = (GradientDrawable) tvFlag.getBackground();
//        drawable.setColor(Color.parseColor(taskDateBeen.getData().get(position % 10).getEventColor()));
        if (taskDateBeen.getData().get(position % 10).getType().equals("1")) {//个人任务
            ivIcon.setImageResource(R.mipmap.icon_hp_dan);
        } else {//协同任务
            ivIcon.setImageResource(R.mipmap.icon_hp_he);
        }
        StringBuilder sb = new StringBuilder("");
        for (TaskListBean.UserListBean bean : taskDateBeen.getUserList()) {
            if (taskDateBeen.getData().get(position % 10).getID().equals(bean.getTaskID())) {
                sb.append(bean.getUserName()).append("，");
            }
        }
        if (TextUtils.isEmpty(sb)) {
            tvJoinName.setVisibility(View.GONE);
        } else {
            tvJoinName.setText("参与人：" + sb.substring(0, sb.length() - 1));
            tvJoinName.setVisibility(View.VISIBLE);
        }
        return view;
    }
}
