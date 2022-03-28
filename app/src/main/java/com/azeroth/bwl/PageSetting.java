package com.azeroth.bwl;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.azeroth.model.SpBucket;
import com.azeroth.utility.GlideCircleTransform;
import com.bumptech.glide.Glide;

import static android.content.Context.MODE_PRIVATE;

public class PageSetting extends Page {
    public PageSetting(BwActivity activity){
        super(activity,View.inflate(activity,R.layout.page_setting,null));
    }
    @Override
    public void initView() throws Exception {
        this.view.findViewById(R.id.Logout).setOnClickListener(this::btnLogoutOnClick);
    }
    @Override
    public void initData() throws Exception {
        ((TextView)this.view.findViewById(R.id.me_user_name_tv)).setText(BwApplication.appInstance.userInfo.TR_NAME);
        ImageView imgview=(ImageView) this.view.findViewById(R.id.imageView_head);
        Glide.with(this.hostActivity).load(BwApplication.appInstance.userInfo.TR_HEADIMGURL)
                .transform(new GlideCircleTransform(this.hostActivity)).into(imgview);
    }

    void btnLogoutOnClick(View view){
        SharedPreferences sp = this.hostActivity.getSharedPreferences(SpBucket.Index.Global, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(SpBucket.Item.UserInfo);
        editor.commit();
        Intent it = new Intent();
        it.setClass(this.hostActivity, LoginActivity.class);
        this.hostActivity.startActivity(it);
        this.hostActivity.finish();
    }


}
