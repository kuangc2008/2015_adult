package com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by chengkuang on 15/9/27.
 */
public class CustomViewListActivity extends BaseActivity {

    private String[] hehe = new String[] {
            "另一种方式实现的，会动的园"
    };

    private Class[] classz = new Class[] {
        CustomView_Circule.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listview = new ListView(this);
        listview.setAdapter(new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, hehe));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(CustomViewListActivity.this, classz[position]);
                startActivity(i);
            }
        });
        setContentView(listview);
    }
}
