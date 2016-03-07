package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kuangcheng01 on 2015/10/30.
 */
public class ListItemViewActivity extends BaseActivity {
    private List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDatas();
        final ListView lv = new ListView(this);
        lv.setAdapter(new MyAdapter());
        setContentView(lv, new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            int oldFirstVisibleItem = 0;
            int oldLastVisibleItem = 0;
            int oldScrollState = SCROLL_STATE_IDLE;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 从fling状态变成idle状态
                if(scrollState == SCROLL_STATE_IDLE && oldScrollState == SCROLL_STATE_FLING) {
                    oldFirstVisibleItem =  lv.getFirstVisiblePosition()  ;  //这个没用...
                    oldLastVisibleItem = lv.getFirstVisiblePosition() + 1;
                    computeEnter(lv.getFirstVisiblePosition(), lv.getLastVisiblePosition());
                }
                oldScrollState = scrollState;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(oldScrollState == SCROLL_STATE_FLING) {
                    return;
                }
                int lastVisibleItem = firstVisibleItem + visibleItemCount - 1;
                computeEnter(firstVisibleItem, lastVisibleItem);
                oldFirstVisibleItem = firstVisibleItem;
                oldLastVisibleItem = lastVisibleItem;
            }

            private void computeEnter(int firstVisibleItem, int lastVisibleItem) {
                if (firstVisibleItem < oldFirstVisibleItem) {
                    for(int i = firstVisibleItem + 1; i <= oldFirstVisibleItem; i++) {
                        onEnter(i);
                    }

                    if(firstVisibleItem == 0 && oldScrollState == 0) {
                        onEnter(0);
                    }
                }

                if (lastVisibleItem > oldLastVisibleItem) {
                    // 注意，此处用的是小于， 也就是需要完全显示出来才会算入
                    for(int i = oldLastVisibleItem; i < lastVisibleItem; i++ ) {
                        if( i >= 0) {
                            onEnter(i);
                        }
                    }

                    if(lastVisibleItem == list.size() - 1) {
                        onEnter(lastVisibleItem);
                    }
                }
            }
        });
    }

    private void onEnter(int i) {
        Log.i("kcc", "onEnter:" + list.get(i) );
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    private void initDatas() {
        int index = 0;
        for(int i=0; i<5;i++) {
            list.add("项目" + index++);
        }
        list.add("广告1");
        for(int i=0; i<40;i++) {
            list.add("项目" + index++);
        }
        list.add("广告2");
        for(int i=0; i<55;i++) {
            list.add("项目" + index++);
        }
        list.add("广告3");
    }


    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = null;
            if(convertView != null) {
                tv = (TextView) convertView;
            } else {
                tv = new TextView(ListItemViewActivity.this);
                tv.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
            }

            tv.setText(list.get(position));
            return tv;
        }
    }
}
