package com.view.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

public class MyFrameLayout extends FrameLayout {

    public MyFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("kcc", this + " new");
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i("kcc", this + " onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i("kcc", this + " onLayout" +  getWidth());
        super.onLayout(changed, l, t, r, b);

    }
}