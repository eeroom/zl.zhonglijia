package com.azeroth.bwl;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
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
    }


}
