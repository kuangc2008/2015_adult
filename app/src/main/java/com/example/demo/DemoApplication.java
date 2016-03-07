package com.example.demo;

import android.app.Application;
import android.provider.Settings;

import com.constant.GLobal;

/**
 * Created by kuangcheng on 2014/9/30.
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GLobal.mContext = this;
    }
}
