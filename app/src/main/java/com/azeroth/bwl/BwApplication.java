package com.azeroth.bwl;

import android.app.Application;

import com.azeroth.model.UserInfo;

public class BwApplication extends Application {

    public static BwApplication appInstance;

    public UserInfo userInfo;
    @Override
    public void onCreate() {
        super.onCreate();
        appInstance=this;
        this.userInfo=new UserInfo();
        this.userInfo.Id="79";
        this.userInfo.NickName="何涛";
    }
}
