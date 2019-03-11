package com.azeroth.bwl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.azeroth.model.SpBucket;

public class SplashActivity extends BwActivity implements Animation.AnimationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() throws Exception {
        setContentView(R.layout.activity_splash);
        LinearLayout layout= (LinearLayout)this.findViewById(R.id.layoutSplash);
        AlphaAnimation alAnimation=new AlphaAnimation(0,255);
        alAnimation.setDuration(500);
        alAnimation.setAnimationListener(this);
        layout.setAnimation(alAnimation);
//        RotateAnimation ra=new RotateAnimation(0f,360f,Animation.RELATIVE_TO_SELF,
//                0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//        ra.setDuration(1000);
//        ra.setAnimationListener(this);
//        layout.setAnimation(ra);
        this.initData();
    }

    @Override
    public void initData() throws Exception {

    }



    @Override
   public   void onAnimationStart(Animation animation){

    }

    @Override
    public  void onAnimationEnd(Animation animation){
        Intent it=new Intent();
        it.setClass(this,GuidActivity.class);
        this.startActivity(it);
        this.finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation){

    }



}
