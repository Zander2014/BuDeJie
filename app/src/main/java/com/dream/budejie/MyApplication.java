package com.dream.budejie;

import android.app.Application;

import org.xutils.x;

/**
 * Created by jerry on 2015/12/20.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
