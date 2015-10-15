package com.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class FloatActionButtonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setText("haha");
        setContentView(tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("hehe", Context.MODE_PRIVATE);
                int a = sp.getInt("gaga", 10);
                Log.i("kcc", "a is ->" + a);
            }
        });


        SharedPreferences sp = getSharedPreferences("hehe", Context.MODE_PRIVATE);
        sp.edit().putString("gaga", "1").commit();
    }


}
