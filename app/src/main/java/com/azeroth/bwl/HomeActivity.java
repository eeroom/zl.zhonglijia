package com.azeroth.bwl;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.azeroth.model.SpBucket;

public class HomeActivity extends BwActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent it= this.getIntent();
        String value= it.getStringExtra(SpBucket.Item.UserInfo);
        ((TextView)this.findViewById(R.id.homeTxtUserInfo)).setText(value);
        this.findViewById(R.id.homeBtnQuit).setOnClickListener(this.wrapperOnclickListener(x->this.btnQuitOnclick(x)));
    }

    public  void btnQuitOnclick(View view){
        SharedPreferences sp= this.getSharedPreferences(SpBucket.Index.Login,MODE_PRIVATE);
        SharedPreferences.Editor editor= sp.edit();
        editor.remove(SpBucket.Item.UserInfo);
        editor.commit();
        Intent it=new Intent();
        it.setClass(this,LoginActivity.class);
        this.startActivity(it);
        this.finish();
    }
}
