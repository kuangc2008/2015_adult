package com.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.annotation.ContentView;
import com.annotation.OnClick;
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
        ViewInjectUtils.injectViews(this);
        ViewInjectUtils.injectClicks(this);
    }


    @OnClick( {R.id.button1, R.id.button2})
    public void clickButtonInvoked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                Log.i("kcc", "button1 clicked");
                break;
            case R.id.button2:
                Log.i("kcc", "button2 clicked");
                break;

        }
    }
}
