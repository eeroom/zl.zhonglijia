package com.azeroth.bwl;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.azeroth.utility.Action2;
import com.azeroth.utility.Function3;

import java.util.List;

public class BwPagerAdapter<T> extends PagerAdapter {
    List<T> lstData;

    Function3<List<T>,ViewGroup,Integer,View>  instantiateItemHandler;

    public BwPagerAdapter(List<T> lstData){
        this.lstData=lstData;
    }


    @Override
    public int getCount() {
        return this.lstData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //return super.instantiateItem(container, position);
        View view=  this.instantiateItemHandler.run(this.lstData,container,position);
        container.addView(view);
        return view;
    }
}
