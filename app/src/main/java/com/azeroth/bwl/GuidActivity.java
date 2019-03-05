package com.azeroth.bwl;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class GuidActivity extends BwActivity implements ViewPager.OnPageChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);
        ViewPager vpview= (ViewPager)this.findViewById(R.id.guidVpview);
        vpview.setAdapter(new GuidAdapter(this));
        vpview.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        ImageView imgv= (ImageView) this.findViewById(R.id.guidImgRedPoint);
        //position*(形状宽度+间距)+偏移百分百*间距，position从0开始，所以不需要-1
        float offset= position*(10+10)+positionOffset*10.0f;
        RelativeLayout.LayoutParams parameter=(RelativeLayout.LayoutParams)imgv.getLayoutParams();
        parameter.leftMargin=dip2px(this,offset) ;
        imgv.setLayoutParams(parameter);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class GuidAdapter extends  PagerAdapter{
        Context context;
        ArrayList<ImageView> lstImgView;
        GuidAdapter(Context context){
            this.context=context;
            this.lstImgView=new ArrayList<ImageView>();
            ImageView view=new ImageView(context);
            view.setBackgroundResource(R.drawable.guid001);
            this.lstImgView.add(view);
            view=new ImageView(context);
            view.setBackgroundResource(R.drawable.guid002);
            this.lstImgView.add(view);
            view=new ImageView(context);
            view.setBackgroundResource(R.drawable.guid003);
            this.lstImgView.add(view);
        }


        @Override
        public int getCount() {
            return this.lstImgView.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view=this.lstImgView.get(position);
            container.addView(view);
            return  view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(this.lstImgView.get(position));
            //super.destroyItem(container, position, object);
        }
    }
}
