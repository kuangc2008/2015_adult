package com.view.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

public  class MyLinearLayout extends LinearLayout {

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("kcc", this + " new");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i("kcc", this + " onMeasure" + getWidth());
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i("kcc", this + " onLayout");
        super.onLayout(changed, l, t, r, b);

    }
}