package com.activity;

import android.app.Activity;
import android.os.Bundle;

import com.view.BitmapTransSurfaceView;

/**
 * Created by chengkuang on 15/7/12.
 */
public class BitmapTransActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BitmapTransSurfaceView view = new BitmapTransSurfaceView(this);
        setContentView(view);

    }



}
