package com.constant;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import com.example.demo.Constants;

/**
 * Created by chengkuang on 15/7/12.
 */
public class GLobal {

    private static boolean isInited = false;

    public static Context mContext;

    public static float mDenstity;
    public static int mDenstiryDpi;
    public static int mWidthPixels;
    public static int mHeightPixels;

    public static void init(Activity activity) {
        if(!isInited) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            mDenstity = dm.density;
            mDenstiryDpi = dm.densityDpi;
            mWidthPixels = dm.widthPixels;
            mHeightPixels = dm.heightPixels;
            isInited = true;
        }
    }
}
