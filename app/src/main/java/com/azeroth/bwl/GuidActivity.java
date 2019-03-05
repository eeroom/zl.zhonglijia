package com.azeroth.bwl;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class GuidActivity extends BwActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);
        ViewPager vpview= (ViewPager)this.findViewById(R.id.guidVpview);
        vpview.setAdapter(new GuidAdapter(this));
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
