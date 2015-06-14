package com.ayoprez.deilylang;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by AyoPrez on 14/06/15.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
