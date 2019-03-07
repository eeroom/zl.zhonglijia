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
        this.hostActivity.SendSoapRequest(message,a->initListView(a));
    }

    public void initListView(SoapObject result){
        SoapObject provinceSoapObject = (SoapObject) result.getProperty(API.ERP.Action.JPushGetJMessageTypeV2 + "Result");
        String json = provinceSoapObject.getProperty(0).toString();
        String json2 = provinceSoapObject.getProperty(1).toString();
        ArrayList<JpushNoticeTypeBean> lstNotice=
                com.alibaba.fastjson.JSON.parseObject(json2,new TypeReference<ArrayList<JpushNoticeTypeBean>>() {});
        ListView lstView= (ListView)this.view.findViewById(R.id.lv_type);
        lstView.setAdapter(new MsgAdapter(this.hostActivity,lstNotice));
    }



    public class MsgAdapter extends BaseAdapter {
        private BwActivity context;
        private List<JpushNoticeTypeBean> lstNotice;

        public MsgAdapter(BwActivity context, List<JpushNoticeTypeBean> lstNotice) {

            this.context=context;
            this.lstNotice=lstNotice;
        }

        @Override
        public int getCount() {
            return lstNotice.size();
        }

        @Override
        public Object getItem(int position) {
             return lstNotice.get(position);
        }

        @Override
        public long getItemId(int position) {
            return this.lstNotice.get(position).hashCode();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView!=null)
                return convertView;
            convertView = View.inflate(this.context,R.layout.page_msg_item,null);
            JpushNoticeTypeBean notice=this.lstNotice.get(position);
            ((TextView) convertView.findViewById(R.id.tv_type_name)).setText(notice.getName());
            ((TextView) convertView.findViewById(R.id.tv_all_num)).setText("全部："+(Integer.valueOf(notice.getIsReadNum())+Integer.valueOf(notice.getNoReadNum())));
            ((TextView) convertView.findViewById(R.id.tv_unread)).setText("未读："+Integer.valueOf(notice.getNoReadNum()));
            ((TextView) convertView.findViewById(R.id.tv_read)).setText("已读："+Integer.valueOf(notice.getIsReadNum()));
            ImageView imgView=(ImageView) convertView.findViewById(R.id.iv_tag);
            new Thread(this.context.wrapperRunnable(()->{
                URL picUrl = new URL(notice.getIconUrl());
                Bitmap pngBM = BitmapFactory.decodeStream(picUrl.openStream());
                this.context.handler.post(this.context.wrapperRunnable(()->imgView.setImageBitmap(pngBM)));
            })).start();
            return convertView;
        }
    }
}
