package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class FloatActionButtonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setText("haha");
        setContentView(tv);
    }
}
