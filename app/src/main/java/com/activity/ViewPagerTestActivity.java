package com.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adult_zeren.R;
import com.view.test.MyFrameLayout;
import com.view.test.MyTextView;

/**
 * Created by kuangcheng01 on 2015/10/20.
 */
public class ViewPagerTestActivity extends BaseActivity {
    private ViewPager mMyViewPager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_test_activity);

        mMyViewPager = (ViewPager) findViewById(R.id.view_pager);
        mMyViewPager.setAdapter(new MyAdapter(this.getSupportFragmentManager()));
        final LinearLayout layout = (LinearLayout) findViewById(R.id.myFrameLayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("kcc2", "22 click");
                MyTextView view = new MyTextView(getBaseContext());
                view.setText("newnewnew");
                layout.addView(view);
            }
        });
        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i("kcc2", "22 onLongClick");
                return false;
            }
        });

        findViewById(R.id.textview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("kcc2", "11 click");
            }
        });
    }

    public static class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return MyFragment.newInstance(i);
        }

        @Override
        public int getCount() {
            return 10;
        }
    }

    public static class MyFragment extends Fragment {
        public static final MyFragment newInstance(int i) {
            MyFragment f = new MyFragment();
            Bundle b = new Bundle();
            b.putInt("hehe", i);
            f.setArguments(b);
            return  f;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Log.i("kcc", this + " onCreateView :" + getArguments().getInt("hehe"));
            MyTextView tv = new MyTextView(this.getActivity());
            tv.setText("���ǵڼ���activity" + getArguments().getInt("hehe"));
            return tv;
        }
    }



}
