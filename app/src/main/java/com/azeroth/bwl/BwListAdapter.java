package com.azeroth.bwl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.azeroth.model.JpushNoticeTypeBean;
import com.azeroth.utility.Function2;
import com.azeroth.utility.Function3;

import java.net.URL;
import java.util.List;

public class BwListAdapter<T> extends BaseAdapter {

    private BwActivity context;
    private List<T> lstValue;
    public Function3<BwActivity,List<T>,Integer,View> createViewHandler;
    public BwListAdapter(BwActivity context, List<T> lst) {

        this.context=context;
        this.lstValue=lst;
    }

    @Override
    public int getCount() {

        return lstValue.size();
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
        if(convertView!=null)
            return convertView;
        if(this.createViewHandler==null)
            throw new RuntimeException("createViewHandler不能为null");
        return this.createViewHandler.run(this.context,this.lstValue,position);
    }
}
