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
//        this.userInfo.Id="159";
//        this.userInfo.Tid="test-c3ec8232-0269-4970-ae4a";
//        this.userInfo.NickName="何涛";

        cn.jpush.android.api.JPushInterface.setDebugMode(true);
        cn.jpush.android.api.JPushInterface.init(this);
    }
}
