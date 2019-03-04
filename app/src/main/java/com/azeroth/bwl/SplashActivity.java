package com.azeroth.bwl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

public class SplashActivity extends BwActivity implements Animation.AnimationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        LinearLayout layout= (LinearLayout)this.findViewById(R.id.layoutSplash);
        RotateAnimation ra=new RotateAnimation(0f,360f,Animation.RELATIVE_TO_SELF,
                0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        ra.setDuration(500);
        layout.setAnimation(ra);
        ra.setAnimationListener(this);
    }
    @Override
   public   void onAnimationStart(Animation animation){

    }

    @Override
    public  void onAnimationEnd(Animation animation){

        Intent it=new Intent();
        it.setClass(this,LoginActivity.class);
        this.startActivity(it);
        this.finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation){

    }


}
