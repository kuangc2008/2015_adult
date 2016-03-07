package com.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.adult_zeren.R;


public class FloatActionButtonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        TextView tv = new TextView(this);
//        tv.setText("haha");
//        setContentView(tv);
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sp = getSharedPreferences("hehe", Context.MODE_PRIVATE);
//                int a = sp.getInt("gaga", 10);
//                Log.i("kcc", "a is ->" + a);
//            }
//        });
//
//
//        SharedPreferences sp = getSharedPreferences("hehe", Context.MODE_PRIVATE);
//        sp.edit().putString("gaga", "1").commit();


        //2  textveiw的size和layout的size的对比

        setContentView(R.layout.textheight_test);
        final View v = findViewById(R.id.hehe_test);
        final TextView tv2 = (TextView) findViewById(R.id.textview2);
        final TextView tv1 = (TextView) findViewById(R.id.textView1);
        v.post(new Runnable() {
            @Override
            public void run() {
                Log.i("kcc", "v.height:" + v.getHeight());
                Log.i("kcc", "textView->" +  tv2.getHeight()  );
                Log.i("kcc", "textView->" +  tv1.getHeight()  );
            }
        });

    }


}
