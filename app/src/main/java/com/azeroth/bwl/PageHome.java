package com.azeroth.bwl;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.azeroth.model.TaskListBean;
import com.azeroth.utility.API;
import com.azeroth.utility.SoapRequestMessage;

import org.ksoap2.serialization.SoapObject;

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
        message.parameter.put("UR_TR_UNION_ID",BwApplication.appInstance.userInfo.Tid);
        message.action=API.ERPTask.Action.getMyTaskListForApp;
        this.hostActivity.SendSoapRequest(message,this::handlerTaskInfo);
    }

    void handlerTaskInfo(SoapObject result){
        SoapObject provinceSoapObject = (SoapObject) result.getProperty(API.ERPTask.Action.getMyTaskListForApp + "Result");
        String json = provinceSoapObject.getProperty(0).toString();
        String json2 = provinceSoapObject.getProperty(1).toString();
        ArrayList<TaskListBean> lstTask=
                com.alibaba.fastjson.JSON.parseObject(json2,new TypeReference<ArrayList<TaskListBean>>() {});
        TaskListBean taskWrapper=lstTask.get(0);
        BwListAdapter<TaskListBean.UserListBean> adapter=new BwListAdapter<>(this.hostActivity,taskWrapper.getUserList());
        adapter.createViewHandler=this::creatTaskItemViewByUserBean;
        ListView lvUser= (ListView)this.view.findViewById(R.id.home_lv_task_user);
        lvUser.setAdapter(adapter);

        BwListAdapter<TaskListBean.DiscussListBean> adapterDiscuss=new BwListAdapter<>(this.hostActivity,taskWrapper.getDiscussList());
        adapterDiscuss.createViewHandler=this::creatTaskItemViewByDiscussBean;
        ListView lvDiscuss= (ListView)this.view.findViewById(R.id.home_lv_task_discuss);
        lvDiscuss.setAdapter(adapterDiscuss);
    }

    View creatTaskItemViewByUserBean(BwActivity context, List<TaskListBean.UserListBean> lstValue, int position){
        View view=View.inflate(context,R.layout.page_home_task_item,null);
        TaskListBean.UserListBean model= lstValue.get(position);
        ((TextView)view.findViewById(R.id.tv_title)).setText(model.getUserName());
        return view;
    }

    View creatTaskItemViewByDiscussBean(BwActivity context, List<TaskListBean.DiscussListBean> lstValue, int position){
        View view=View.inflate(context,R.layout.page_home_task_item,null);
        TaskListBean.DiscussListBean model= lstValue.get(position);
        ((TextView)view.findViewById(R.id.tv_title)).setText(model.getUserName());
        return view;
    }


}
