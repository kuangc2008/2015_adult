package com.view.test;


import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
        Log.i("kcc", this + " new");
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("kcc", this + " new");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i("kcc", this + " onMeasure:" + getText().toString());
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i("kcc2", this + " onLayout:" + getText().toString());
        super.onLayout(changed, l, t, r, b);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("kcc2", this + " onDraw:" + getText().toString());
        super.onDraw(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("kcc2", "onTOuchEvent->", new Exception());
        return super.onTouchEvent(event);
    }
}
