package com.activity;

import android.os.Bundle;
import android.widget.Button;

import com.annotation.ContentView;
import com.annotation.ViewInjet;
import com.annotation.util.ViewInjectUtils;
import com.example.adult_zeren.R;

/**
 * Created by chengkuang on 15/7/24.
 */
@ContentView(value3 = R.layout.annotation_view)
public class AnnotationActivity extends BaseActivity{

    @ViewInjet(R.id.button1)
    private Button mBtn1;

    @ViewInjet(R.id.button2)
    private Button mBtn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewInjectUtils.injectContentView(this);

    }
}
