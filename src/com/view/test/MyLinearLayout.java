package com.view.test;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
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

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Log.i("kcc2", "onWindowFocusChanged");
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        Log.i("kcc2", "onFocusChanged");
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("kcc2", "onInterceptTouchEvent", new Exception());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("kcc2", "onTouchEvent", new Exception());
        return super.onTouchEvent(event);
    }
}