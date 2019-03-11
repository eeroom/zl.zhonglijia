package com.azeroth.bwl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.azeroth.model.SpBucket;
import com.azeroth.view.ViewPagerNoScroll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BwActivity  {

    HashMap<Integer,Integer> dictRadioAndVpIndex=new HashMap<>();
    ViewPagerNoScroll viewPager;

    @Override
    public void initView() throws Exception {
        setContentView(R.layout.activity_main);
        ((RadioGroup)this.findViewById(R.id.mainRadioGroup)).setOnCheckedChangeListener(this::radioGroupOnCheckedChange);
        this.viewPager=(ViewPagerNoScroll)this.findViewById(R.id.mainViewPage);
        this.initData();
    }
    @Override
    public void initData() throws Exception {
        dictRadioAndVpIndex.put(R.id.mainRadioBtnHome,0);
        dictRadioAndVpIndex.put(R.id.mainRadioBtnTrip,1);
        dictRadioAndVpIndex.put(R.id.mainRadioBtnMsg,2);
        dictRadioAndVpIndex.put(R.id.mainRadioBtnZLQ,3);
        dictRadioAndVpIndex.put(R.id.mainRadioBtnSetting,4);
        ArrayList<Page> lstPage=new ArrayList<Page>();
        lstPage.add(new PageHome(this));
        lstPage.add(new PageTrip(this));
        lstPage.add(new PageMsg(this));
        lstPage.add(new PageZLQ(this));
        lstPage.add(new PageSetting(this));
        BwPagerAdapter<Page> adapter=new BwPagerAdapter(this,lstPage);
        adapter.instantiateItemHandler=(context,lst,position)->lst.get(position).view;
        this.viewPager.setAdapter(adapter);
    }

//    @Override
//    public void onBackPressed() {
//        //super.onBackPressed();
//        Intent home = new Intent(Intent.ACTION_MAIN);
//        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        home.addCategory(Intent.CATEGORY_HOME);
//        startActivity(home);
//
//    }

    public void radioGroupOnCheckedChange(RadioGroup group, int checkedId)
    {
        this.viewPager.setCurrentItem(this.dictRadioAndVpIndex.get(checkedId));
    }


    private int isExit = 0;
    Timer mTimer;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_BACK || event.getAction() != KeyEvent.ACTION_DOWN)
           return  false;
        if(isExit>0)
            System.exit(0);
        isExit++;
        Toast.makeText(this, "再点一下退出", Toast.LENGTH_SHORT).show();
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                isExit = 0;
            }
        }, 1000);
        return false;
    }
}
