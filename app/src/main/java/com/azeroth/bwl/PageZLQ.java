package com.azeroth.bwl;

import android.app.Activity;
import android.view.View;

public class PageZLQ extends Page {

    public PageZLQ(Activity activity){
        super(activity);
    }

    @Override
    public View createView() {
        return View.inflate(this.root,R.layout.page_zlq,null);
    }
}
