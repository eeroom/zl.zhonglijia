package com.azeroth.bwl;

import android.app.Activity;
import android.view.View;

public class PageHome extends Page {
    public PageHome(Activity activity){
        super(activity);
    }
    @Override
    public View createView() {

        return View.inflate(this.root,R.layout.page_home,null);

    }
}
