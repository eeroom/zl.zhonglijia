package com.azeroth.bwl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.azeroth.model.JpushNoticeTypeBean;
import com.azeroth.model.UserInfo;
import com.azeroth.utility.API;
import com.azeroth.utility.RT;
import com.azeroth.utility.SoapRequestMessage;
import com.bumptech.glide.Glide;

import org.ksoap2.serialization.SoapObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PageMsg extends Page {

    public PageMsg(BwActivity activity){
        super(activity,View.inflate(activity,R.layout.page_msg,null));
    }

    @Override
    public void initView() throws  Exception {

    }

    @Override
    public void initData() throws  Exception{
        SoapRequestMessage message=new SoapRequestMessage(API.ERP.BassAdrees);
        message.parameter.put("UnionID", "159");
        message.parameter.put("AppType", "2");
        message.action=API.ERP.Action.JPushGetJMessageTypeV2;
        this.hostActivity.SendSoapRequest(message,this::initListView);
    }

    public void initListView(SoapObject result){
        SoapObject provinceSoapObject = (SoapObject) result.getProperty(API.ERP.Action.JPushGetJMessageTypeV2 + "Result");
        String json = provinceSoapObject.getProperty(0).toString();
        String json2 = provinceSoapObject.getProperty(1).toString();
        ArrayList<JpushNoticeTypeBean> lstNotice=
                com.alibaba.fastjson.JSON.parseObject(json2,new TypeReference<ArrayList<JpushNoticeTypeBean>>() {});
        BwListAdapter<JpushNoticeTypeBean> adapter= new BwListAdapter(this.hostActivity,lstNotice);
        adapter.createViewHandler=this::createMsgItemView;

        ListView lstView= (ListView)this.view.findViewById(R.id.lv_type);
        lstView.setAdapter(adapter);
    }

    View createMsgItemView(BwActivity context,List<JpushNoticeTypeBean> lstValue,int position,Object tag) {
        View view = View.inflate(context,R.layout.page_msg_item,null);
        JpushNoticeTypeBean notice=lstValue.get(position);
        ((TextView) view.findViewById(R.id.tv_type_name)).setText(notice.getName());
        ((TextView) view.findViewById(R.id.tv_all_num)).setText("全部："+(Integer.valueOf(notice.getIsReadNum())+Integer.valueOf(notice.getNoReadNum())));
        ((TextView) view.findViewById(R.id.tv_unread)).setText("未读："+Integer.valueOf(notice.getNoReadNum()));
        ((TextView) view.findViewById(R.id.tv_read)).setText("已读："+Integer.valueOf(notice.getIsReadNum()));
        ImageView imgView=(ImageView) view.findViewById(R.id.iv_tag);
        Glide.with(this.hostActivity).load(notice.getIconUrl()).into(imgView);
//        new Thread(context.wrapperRunnable(()->{
//            URL picUrl = new URL(notice.getIconUrl());
//            Bitmap pngBM = BitmapFactory.decodeStream(picUrl.openStream());
//            context.handler.post(context.wrapperRunnable(()->imgView.setImageBitmap(pngBM)));
//        })).start();
        return view;
    }
}
