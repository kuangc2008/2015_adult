package com.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

/**
 * Created by chengkuang on 15/9/27.
 */
public class CustomView_Circule extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        setContentView(ll);

        CirculeView view = new CirculeView(this);
        view.start();
        ll.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600));

        CirculeView2 view2 = new CirculeView2(this);
        view2.start();
        ll.addView(view2,  new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600));
    }


    /**
     * 这样的好处，是讲逻辑代码从onDraw方法中拿出，更容易看了。。。唉，其实都一样吧。不过有耗时操作的话，不会阻塞主进程。
     */
    public static class CirculeView extends View {
        private Paint paint = null;
        private int mRadius = 200;

        public CirculeView(Context context) {
            super(context);

            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);   //边框的一半到圆心的距离
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawCircle(200, 200, mRadius, paint);
        }


        private void setRadius(int radius) {
            this.mRadius = radius;
            invalidate();
        }

        public void start() {
            ObjectAnimator oa = ObjectAnimator.ofInt(this, "radius", 200, 100);
            oa.setDuration(1000);
            oa.setRepeatCount(Animation.INFINITE);
            oa.setRepeatMode(ValueAnimator.REVERSE);
            oa.setInterpolator(new LinearInterpolator());
            oa.start();
        }
    }


    /**
     * 与上面相比，上面的更平滑些。  因为这里有判断临界点，并且是通过定点相加的； 而上面是通过是时间相加的，刚刚好。
     */
    public static class CirculeView2 extends View implements Runnable{
        private Paint paint = null;
        private int mRadius = 200;
        private boolean isLarge = true;
        public CirculeView2(Context context) {
            super(context);

            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);   //边框的一半到圆心的距离
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawCircle(200, 200, mRadius, paint);
        }


        private void setRadius() {
            changeSize();
            if(mRadius >= 200) {
                mRadius = 200;
                isLarge = !isLarge;
            } else if(mRadius <= 100) {
                mRadius = 100;
                isLarge = !isLarge;
            }
        }

        private void changeSize() {
            if(isLarge) {
                mRadius += 3;
            } else {
                mRadius -= 3;
            }
        }

        public void start() {
            new Thread(this).start();
        }


        @Override
        public void run() {
            while(true) {
                setRadius();
                postInvalidate();
                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
