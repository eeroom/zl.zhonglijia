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
        super(activity);
    }
    @Override
    public View createView() {
        View v=View.inflate(this.root,R.layout.page_msg,null);
        SoapRequestMessage message=new SoapRequestMessage(API.ERP.BassAdrees);
        message.parameter.put("UnionID", "159");
        message.parameter.put("AppType", "2");
        message.action=API.ERP.Action.JPushGetJMessageTypeV2;
        this.root.wrapperRunnable(()->{
            this.root.SendSoapRequest(message,a->initListView(a));
        }).run();
        return v;
    }

    public void initListView(SoapObject result){
        SoapObject provinceSoapObject = (SoapObject) result.getProperty(API.ERP.Action.JPushGetJMessageTypeV2 + "Result");
        String json = provinceSoapObject.getProperty(0).toString();
        String json2 = provinceSoapObject.getProperty(1).toString();
        ArrayList<JpushNoticeTypeBean> lstNotice=
                com.alibaba.fastjson.JSON.parseObject(json2,new TypeReference<ArrayList<JpushNoticeTypeBean>>() {});
        ListView lstView= (ListView)this.view.findViewById(R.id.lv_type);
        lstView.setAdapter(new MsgAdapter(this.root,lstNotice));
    }

    public static void loadImage(Context context, String imgUrl, ImageView imageView) {
////        Glide.with(mContext)
//        Glide.with(ECApplication.getInstance().getmContext())
//                .load(imgUrl)
//                .error(IMAGE)//加载出错时显示的图片
////                .centerCrop()
////                .fitCenter()
////                .dontAnimate()//取消图片加载出来时淡入淡出的特效
////                .skipMemoryCache(true)//跳过缓存到内存中去
////                .diskCacheStrategy( DiskCacheStrategy.NONE )//跳过磁盘缓存
//                .into(imageView);
    }
    public class MsgAdapter extends BaseAdapter {
        private BwActivity mContext;
        private List<JpushNoticeTypeBean> templist;
        public MsgAdapter(BwActivity mContext, List<JpushNoticeTypeBean> templist) {
            this.mContext=mContext;
            this.templist=templist;
        }

        @Override
        public int getCount() {
            return templist.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.page_msg_item, null);
                convertView.setTag(new ViewHolder(convertView));
            }
            initializeViews(position, (ViewHolder) convertView.getTag());
            return convertView;
        }

        private void initializeViews(int position, ViewHolder holder) {
            holder.tvAllNum.setText("全部："+(Integer.valueOf(templist.get(position).getIsReadNum())+Integer.valueOf(templist.get(position).getNoReadNum())));
            holder.tvRead.setText("已读："+Integer.valueOf(templist.get(position).getIsReadNum()));
            holder.tvUnread.setText("未读："+Integer.valueOf(templist.get(position).getNoReadNum()));
            holder.tvTypeName.setText(templist.get(position).getName());
            new Thread(this.mContext.wrapperRunnable(()->{
                URL picUrl = new URL(templist.get(position).getIconUrl());
                Bitmap pngBM = BitmapFactory.decodeStream(picUrl.openStream());
                this.mContext.handler.post(this.mContext.wrapperRunnable(()->{holder.ivTag.setImageBitmap(pngBM);}));
            })).start();
            //holder.ivTag.setImageURI(Uri.parse(templist.get(position).getIconUrl()));
            //loadImage(mContext,templist.get(position).getIconUrl(),holder.ivTag);
//        switch (templist.get(position).getID()) {
//            case "1":
//                holder.ivTag.setImageResource(R.mipmap.kaoqin_tag);
//                break;
//            case "2":
//                holder.ivTag.setImageResource(R.mipmap.renwu);
//                break;
//            case "3":
//                holder.ivTag.setImageResource(R.mipmap.xitong);
//                break;
//            case "4":
//                holder.ivTag.setImageResource(R.mipmap.xitong);
//                break;
//            case "8":
//                holder.ivTag.setImageResource(R.mipmap.good_news);
//                break;
//            case "7":
//                holder.ivTag.setImageResource(R.mipmap.yewu);
//                break;
//            default:
//                holder.ivTag.setImageResource(R.mipmap.moren);
//                break;
//        }

        }

        protected class ViewHolder {
            private ImageView ivTag;
            private TextView tvTypeName;
            private TextView tvAllNum;
            private TextView tvUnread;
            private TextView tvRead;

            public ViewHolder(View view) {
                ivTag = (ImageView) view.findViewById(R.id.iv_tag);
                tvTypeName = (TextView) view.findViewById(R.id.tv_type_name);
                tvAllNum = (TextView) view.findViewById(R.id.tv_all_num);
                tvUnread = (TextView) view.findViewById(R.id.tv_unread);
                tvRead = (TextView) view.findViewById(R.id.tv_read);
            }
        }
    }
}
