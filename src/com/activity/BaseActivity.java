package com.activity;

import android.app.Activity;
import android.os.Bundle;

import com.constant.GLobal;

/**
 * Created by chengkuang on 15/7/12.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLobal.init(this);
    }
}
