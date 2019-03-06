package com.azeroth.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ViewPagerNoScroll extends ViewPager {
    public ViewPagerNoScroll(Context context){
        super(context);
    }
    public ViewPagerNoScroll(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return  true;
    }
}
