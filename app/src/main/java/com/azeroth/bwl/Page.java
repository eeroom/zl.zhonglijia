package com.azeroth.bwl;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

public abstract class Page {

    public  BwActivity hostActivity;
    public View view;

    public Page(BwActivity activity,View partialView){
        this.hostActivity=activity;
        this.view=partialView;
        try {
            this.initView();
            this.initData();
        }catch (Exception ex){
            String msg=ex.getMessage();
            if(msg.isEmpty())
                msg=ex.getClass().getName();
            Toast.makeText(this.hostActivity,ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    public abstract void initData() throws Exception;
    public abstract void  initView() throws Exception;
}
