package com.azeroth.bwl;

import android.app.Activity;
import android.view.View;

public class PageSetting extends Page {
    public PageSetting(BwActivity activity){
        super(activity);
    }
    @Override
    public View createView() {
        return View.inflate(this.root,R.layout.page_setting,null);
    }
}
