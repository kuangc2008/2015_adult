package com.view;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chengkuang on 15/7/26.
 */
public class FlingCardListener implements View.OnTouchListener{

    protected interface FlingListener {
        public void onCardExited();
        public void leftExit(Object dataObject);
        public void rightExit(Object dataObject);
        public void onClick(Object dataObject);
        public void onScroll(float scrollProgressPercent);
    }

    private final float objectX;
    private final float objectY;
    private final int objectH;
    private final int objectW;
    private final int parentWidth;
    private final FlingListener mFlingListener;
    private final Object dataObject;
    private final float halfWidth;
    private float BASE_ROTATION_DEGREES;

    private float aPosX;
    private float aPosY;
    private float aDownTouchX;
    private float aDownTouchY;
    private static final int INVALID_POINTER_ID = -1;

    // The active pointer is the one currently moving our object.
    private int mActivePointerId = INVALID_POINTER_ID;
    private View frame = null;


    private final int TOUCH_ABOVE = 0;
    private final int TOUCH_BELOW = 1;
    private int touchPosition;
    private final Object obj = new Object();
    private boolean isAnimationRunning = false;
    private float MAX_COS = (float) Math.cos(Math.toRadians(45));

    public FlingCardListener(View frame, Object itemAtPostion, float totation_degrees, FlingListener flingListener) {
        this.frame = frame;
        this.objectX = frame.getX();
        this.objectY = frame.getY();
        this.objectW = frame.getWidth();
        this.objectH = frame.getHeight();

        this.halfWidth = objectW/2;
        this.dataObject = itemAtPostion;
        this.parentWidth = ((ViewGroup) frame.getParent()).getWidth();

        this.BASE_ROTATION_DEGREES = totation_degrees;
        this.mFlingListener = flingListener;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mActivePointerId = event.getPointerId(0);
                final float x = event.getX(mActivePointerId);
                final float y = event.getY(mActivePointerId);
                aDownTouchX = x;
                aDownTouchY = y;

                if(aPosX == 0) {
                    aPosX = frame.getX();
                }
                if(aPosY == 0) {
                    aPosY = frame.getY();
                }

                if( y < objectH/2) {
                    touchPosition = TOUCH_ABOVE;
                } else {
                    touchPosition = TOUCH_BELOW;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                final int pointerIndexMove = event.findPointerIndex(mActivePointerId);
                final float xMove = event.getX(pointerIndexMove);
                final float yMove = event.getY(pointerIndexMove);

                final float dx = xMove - aDownTouchX;
                final float dy = yMove - aDownTouchY;

                aPosX += dx;
                aPosY += dy;

                float disObjectX = aPosX - objectX;
                float rotation = BASE_ROTATION_DEGREES * 2.0f * disObjectX / parentWidth;
                if(touchPosition == TOUCH_BELOW) {
                    rotation = -rotation;
                }

                frame.setX(aPosX);
                frame.setY(aPosY);
                frame.setRotation(rotation);
                mFlingListener.onScroll(getScrollProgressPercent());

                break;
            case MotionEvent.ACTION_UP:

                break;
        }

        return true;
    }

    private float getScrollProgressPercent() {
        if( movedBeyondLeftBorder()) {
            return -1f;
        } else if(movedBeyondRightBorder()) {
            return 1f;
        } else {
            float zeroToOneValue = (aPosX + halfWidth - leftBorder()) / (rightBorder() - leftBorder()) ;
            return zeroToOneValue * 2 - 1f;
        }
    }

    private boolean movedBeyondLeftBorder() {
        return aPosX+halfWidth < leftBorder();
    }

    private boolean movedBeyondRightBorder() {
        return aPosX+halfWidth > rightBorder();
    }


    public float leftBorder(){
        return parentWidth/4.f;
    }

    public float rightBorder(){
        return 3*parentWidth/4.f;
    }
}
