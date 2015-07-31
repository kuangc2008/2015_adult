package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.utils.WIfiUtils;

/**
 * Created by chengkuang on 15/7/31.
 */
public class WIfiConnectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String wifi = WIfiUtils.getWifiAddr(this);
        Log.i("kcc", "wifi->" + wifi);
    }
}
