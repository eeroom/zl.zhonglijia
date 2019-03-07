package com.azeroth.bwl;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.azeroth.model.JpushNoticeTypeBean;
import com.azeroth.model.TaskListBean;
import com.azeroth.utility.API;
import com.azeroth.utility.SoapRequestMessage;

import org.ksoap2.serialization.SoapObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
        message.parameter.put("UR_TR_UNION_ID",BwApplication.appInstance.userInfo.Tid);
        message.action=API.ERPTask.Action.getMyTaskListForApp;
        this.hostActivity.SendSoapRequest(message,this::handlerTaskInfo);
    }

    void handlerTaskInfo(SoapObject result){
        SoapObject provinceSoapObject = (SoapObject) result.getProperty(API.ERPTask.Action.getMyTaskListForApp + "Result");
        String json = provinceSoapObject.getProperty(0).toString();
        String json2 = provinceSoapObject.getProperty(1).toString();
        ArrayList<TaskListBean> lstNotice=
                com.alibaba.fastjson.JSON.parseObject(json2,new TypeReference<ArrayList<TaskListBean>>() {});

    }


}
