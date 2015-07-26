package com.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.adult_zeren.R;
import com.view.SwipeFlingAdapterView;

import java.util.ArrayList;

/**
 * Created by chengkuang on 15/7/26.
 */
public class SwipFlingActivity extends BaseActivity {

    private SwipeFlingAdapterView mSwipFlingAdapterView;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swip_fling_view);

        mSwipFlingAdapterView = (SwipeFlingAdapterView) findViewById(R.id.swipe_fling_adapter_view);

        ArrayList<String> al = new ArrayList<String>();
        al.add("php");
        al.add("c");
        al.add("python");
        al.add("java");
        al.add("html");
        al.add("c++");
        al.add("css");
        al.add("javascript");

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.swip_fling_view_item, R.id.helloText, al);
        mSwipFlingAdapterView.setAdapter(arrayAdapter);





    }
}
