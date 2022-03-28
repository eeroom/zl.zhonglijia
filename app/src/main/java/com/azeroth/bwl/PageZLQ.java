package com.azeroth.bwl;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class PageZLQ extends Page {

    static {
       System.loadLibrary("native-lib");
    }
    public PageZLQ(BwActivity activity){
        super(activity,View.inflate(activity,R.layout.page_zlq,null));
    }

    //public native int ageGet();

    public native String  stringFromJNI();

    @Override
    public void initData() throws Exception {
        String value=this.stringFromJNI();
        ((TextView)this.view.findViewById(R.id.tv_title)).setText(value);
    }

    @Override
    public void initView() throws Exception {

    }
}
