package com.azeroth.bwl;

import android.app.Activity;
import android.view.View;

public abstract class Page {

    public  Activity root;

    public View view;

    public Page(Activity activity){
        this.root=activity;
        this.view=this.createView();
    }

    public abstract View createView();
}
