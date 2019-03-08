package com.azeroth.bwl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.azeroth.model.JMessageBean;
import com.azeroth.model.TaskListBean;
import com.azeroth.utility.API;
import com.azeroth.utility.SoapRequestMessage;

import org.ksoap2.serialization.SoapObject;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PageHome extends Page {
    public PageHome(BwActivity activity){
        super(activity,View.inflate(activity,R.layout.page_home,null));
    }
    @Override
    public void initView() throws Exception{

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

    }

    void handlerTipInfo(SoapObject result){
        SoapObject provinceSoapObject = (SoapObject) result.getProperty(API.ERP.Action.JPushGetJMessageLogIndex + "Result");
        String json = provinceSoapObject.getProperty(0).toString();
        String json2 = provinceSoapObject.getProperty(1).toString();
        ArrayList<JMessageBean> lstRT=
                com.alibaba.fastjson.JSON.parseObject(json2,new TypeReference<ArrayList<JMessageBean>>() {});
        BwListAdapter<JMessageBean> adapter=new BwListAdapter<>(this.hostActivity,lstRT);
        adapter.createViewHandler=this::creatTipItemView;
        ListView lvTip= (ListView)this.view.findViewById(R.id.home_lv_tip);
        lvTip.setAdapter(adapter);
    }

    View creatTipItemView(BwActivity context, List<JMessageBean> lstValue, int position,Object tag){
        View view=View.inflate(context,R.layout.page_home_tip_item,null);
        ImageView  ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvDate = (TextView) view.findViewById(R.id.tv_date);
        JMessageBean model=lstValue.get(position);
        tvTitle.setText(model.getTitle());
        tvDate.setText(model.getNewTime());
        new Thread(context.wrapperRunnable(()->{
            URL picUrl = new URL(model.getHeadImg());
            Bitmap pngBM = BitmapFactory.decodeStream(picUrl.openStream());
            context.handler.post(context.wrapperRunnable(()->ivIcon.setImageBitmap(pngBM)));
        })).start();
        return view;
        //ImageLoaderUtil.loadImage(context,newsListsBean.get(position).getHeadImg(),holder.ivIcon);
    }

    void handlerTaskInfo(SoapObject result){
        SoapObject provinceSoapObject = (SoapObject) result.getProperty(API.ERPTask.Action.getMyTaskListForApp + "Result");
        String json = provinceSoapObject.getProperty(0).toString();
        String json2 = provinceSoapObject.getProperty(1).toString();
        ArrayList<TaskListBean> lstRT=
                com.alibaba.fastjson.JSON.parseObject(json2,new TypeReference<ArrayList<TaskListBean>>() {});
        TaskListBean taskWrapper=lstRT.get(0);
        BwListAdapter<TaskListBean.DataBean> adapter=new BwListAdapter<>(this.hostActivity,taskWrapper.getData(),taskWrapper);
        adapter.createViewHandler=this::creatTaskItemView;
        ListView lvTask= (ListView)this.view.findViewById(R.id.home_lv_task);
        lvTask.setAdapter(adapter);
    }

    View creatTaskItemView(BwActivity context, List<TaskListBean.DataBean> lstValue, int position,Object tag){
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
