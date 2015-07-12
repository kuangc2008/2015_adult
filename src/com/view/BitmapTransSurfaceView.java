package com.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.constant.GLobal;
import com.example.adult_zeren.R;


/**
 * Created by chengkuang on 15/7/12.
 */
public class BitmapTransSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private boolean flag = false;
    private SurfaceHolder mHolder = null;
    private Bitmap mBitmap = null;
    private Paint mTextPaint = null;

    public BitmapTransSurfaceView(Context context) {
        super(context);
        mHolder = getHolder();
        mHolder.addCallback(this);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.th);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.RED);
        mTextPaint.setTextSize(16 * GLobal.mDenstity);
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            while(flag) {
                long start = System.currentTimeMillis();
                logic();
                myDraw();
                long end = System.currentTimeMillis();
                if(end - start < 30) {
                    try {
                        sleep((30) - (end - start));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        
    }

    private void myDraw() {
        Canvas canvas = null;
        try {
            canvas = mHolder.lockCanvas();
            canvas.drawBitmap(mBitmap, 0, 0, null);
            String text = "width:" + mBitmap.getWidth() + " height:" + mBitmap.getHeight();
            canvas.drawText(text, mBitmap.getWidth(), mBitmap.getHeight()/2, mTextPaint);
        } catch (Exception e) {
        }finally {
            mHolder.unlockCanvasAndPost(canvas);
        }





    }

    private void logic() {
        
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        flag = true;
        Thread thread = new MyThread();
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;

    }
}
