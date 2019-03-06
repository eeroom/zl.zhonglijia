package com.azeroth.bwl;

import android.app.Activity;
import android.view.View;

public abstract class Page {

    public  BwActivity root;

    public View view;

    public Page(BwActivity activity){
        this.root=activity;
        this.view=this.createView();
    }

    public abstract View createView();
}
