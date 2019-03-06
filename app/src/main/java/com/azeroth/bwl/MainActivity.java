package com.azeroth.bwl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.azeroth.model.SpBucket;
import com.azeroth.view.ViewPagerNoScroll;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends BwActivity {
    ArrayList<Page> lstPage=new ArrayList<Page>();
    HashMap<Integer,Integer> dictRadioAndVpIndex=new HashMap<>();
    ViewPagerNoScroll viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent it= this.getIntent();
        String value= it.getStringExtra(SpBucket.Item.UserInfo);
        //((TextView)this.findViewById(R.id.homeTxtUserInfo)).setText(value);
       // this.findViewById(R.id.homeBtnQuit).setOnClickListener(this.wrapperOnclickListener(x->this.btnQuitOnclick(x)));
        ((RadioGroup)this.findViewById(R.id.mainRadioGroup)).setOnCheckedChangeListener(this::radioGroupOnCheckedChange);
        dictRadioAndVpIndex.put(R.id.mainRadioBtnHome,0);
        dictRadioAndVpIndex.put(R.id.mainRadioBtnTrip,1);
        dictRadioAndVpIndex.put(R.id.mainRadioBtnMsg,2);
        dictRadioAndVpIndex.put(R.id.mainRadioBtnZLQ,3);
        dictRadioAndVpIndex.put(R.id.mainRadioBtnSetting,4);
        this.lstPage.add(new PageHome(this));
        this.lstPage.add(new PageTrip(this));
        this.lstPage.add(new PageMsg(this));
        this.lstPage.add(new PageZLQ(this));
        this.lstPage.add(new PageSetting(this));
        this.viewPager=(ViewPagerNoScroll)this.findViewById(R.id.mainViewPage);
        this.viewPager.setAdapter(new VPAdapter());
    }

    public class  VPAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return MainActivity.this.lstPage.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //return super.instantiateItem(container, position);
            View view=MainActivity.this.lstPage.get(position).view;
            container.addView(view);
            return  view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View)object);
        }
    }

    public void radioGroupOnCheckedChange(RadioGroup group, int checkedId)
    {
        this.viewPager.setCurrentItem(this.dictRadioAndVpIndex.get(checkedId));
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
