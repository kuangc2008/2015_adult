package com.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

import com.fragment.SimpleRecyclerViewFragment;

/**
 * Created by chengkuang on 15/7/12.
 */
public class RecyclerViewActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout mContent = new FrameLayout(getBaseContext());
        mContent.setId(android.R.id.content);
        setContentView(mContent);

        if(savedInstanceState == null) {
            FragmentManager mFm = getSupportFragmentManager();
            mFm.beginTransaction().add(android.R.id.content, new SimpleRecyclerViewFragment(), "recycle").commit();
        }
    }


}
