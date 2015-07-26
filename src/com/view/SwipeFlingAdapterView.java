package com.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.FrameLayout;

/**
 * Created by chengkuang on 15/7/26.
 */
public class SwipeFlingAdapterView extends BaseFlingAdapterView {

    private int LAST_OBJECT_IN_STACK = 0;
    private int MAX_VISIBLE = 4;

    private Adapter mAdapter;

    private OnFlingListener mFlingListener;
    private OnItemClickListener mOnItemClickListener;
    private FlingCardListener flingCardListener;

    private AdapterDataSetObserver mDataSetObserver;
    private boolean mInLayout = false;
    private View mActiveCard = null;


    public interface OnItemClickListener {
        public void onItemClicked(int itemPos, Object dataObj);
    }

    public interface OnFlingListener {
        public void removeFirstObjectInAdapter();
        public void onLeftCardExit(Object dataObj);
        public void onRightCardExit(Object dataObj);
        public void onAdapterAboutToEmpty(int itemsInApater);
        public void onScroll(float scrollProgressPercent);
    }


    public SwipeFlingAdapterView(Context context) {
        super(context);
    }

    public SwipeFlingAdapterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeFlingAdapterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setFlingListener(OnFlingListener onFlingListener) {
        this.mFlingListener = onFlingListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }


    @Override
    public void requestLayout() {
        if(!mInLayout) {
            super.requestLayout();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if(mAdapter == null) {
            return;
        }

        mInLayout = true;
        final int adapterCount = mAdapter.getCount();

        if(adapterCount == 0) {
            removeAllViewsInLayout();
        } else {
            View topCard = getChildAt(LAST_OBJECT_IN_STACK);
            if(mActiveCard != null && topCard != null && topCard == mActiveCard) {
                removeViewsInLayout(0, LAST_OBJECT_IN_STACK);
                layoutChildren(1, adapterCount);
            } else {
                removeAllViewsInLayout();
                layoutChildren(0, adapterCount);
                setTopView();
            }
        }

        mInLayout = false;
        if(adapterCount < MAX_VISIBLE) {
            mFlingListener.onAdapterAboutToEmpty(adapterCount);
        }
    }

    private void layoutChildren(int startingIndex, int adapterCount) {
        while(startingIndex < Math.min(adapterCount, MAX_VISIBLE)) {
            View newUnderChild = mAdapter.getView(startingIndex, null, this);
            if(newUnderChild.getVisibility() != View.GONE) {
                makeAndAddView(newUnderChild);
                LAST_OBJECT_IN_STACK = startingIndex;
            }
            startingIndex++;
        }
    }

    private void makeAndAddView(View child) {
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) child.getLayoutParams();
        addViewInLayout(child, 0, lp, true);

        final boolean need2Measure = child.isLayoutRequested();
        if(need2Measure) {
            int childWidthSpec = getChildMeasureSpec(getWidthMeasureSpec(),
                    getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin,
                    lp.width);
            int childHeightSpec = getChildMeasureSpec(getHeightMeasureSpec(),
                    getPaddingTop() + getPaddingBottom() + lp.topMargin + lp.bottomMargin,
                    lp.height);

            child.measure(childWidthSpec, childHeightSpec);
        } else {
            cleanupLayoutState(child);
        }


        int w = child.getMeasuredWidth();
        int h = child.getMeasuredHeight();
        int gravity = lp.gravity;

        if(gravity == -1) {
            gravity = Gravity.TOP | Gravity.START;
        }

        int layoutDirection = getLayoutDirection();
        final int absoluteGravity = Gravity.getAbsoluteGravity(gravity, layoutDirection);
        final int verticalGravity = gravity & Gravity.VERTICAL_GRAVITY_MASK;

        int childLeft;
        int childTop;
        switch (absoluteGravity & Gravity.HORIZONTAL_GRAVITY_MASK) {
            case Gravity.CENTER_HORIZONTAL:
                childLeft = (getWidth() + getPaddingLeft() - getPaddingRight()  - w) / 2 +
                        lp.leftMargin - lp.rightMargin;
                break;
            case Gravity.END:
                childLeft = getWidth() + getPaddingRight() - w - lp.rightMargin;
                break;
            case Gravity.START:
            default:
                childLeft = getPaddingLeft() + lp.leftMargin;
                break;
        }
        switch (verticalGravity) {
            case Gravity.CENTER_VERTICAL:
                childTop = (getHeight() + getPaddingTop() - getPaddingBottom()  - h) / 2 +
                        lp.topMargin - lp.bottomMargin;
                break;
            case Gravity.BOTTOM:
                childTop = getHeight() - getPaddingBottom() - h - lp.bottomMargin;
                break;
            case Gravity.TOP:
            default:
                childTop = getPaddingTop() + lp.topMargin;
                break;
        }

        child.layout(childLeft, childTop, childLeft + w, childTop + h);

    }

    public void setTopView() {
        if(getChildCount() > 0) {
            mActiveCard = getChildAt(LAST_OBJECT_IN_STACK);
            if(mActiveCard != null) {
                flingCardListener = new FlingCardListener(mActiveCard, mAdapter.getItem(0),
                        40, new FlingCardListener.FlingListener() {
                    @Override
                    public void onCardExited() {
                    }

                    @Override
                    public void leftExit(Object dataObject) {

                    }

                    @Override
                    public void rightExit(Object dataObject) {

                    }

                    @Override
                    public void onClick(Object dataObject) {

                    }

                    @Override
                    public void onScroll(float scrollProgressPercent) {

                    }
                });
                mActiveCard.setOnTouchListener(flingCardListener);
            }
        }
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new FrameLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    public Adapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if(mAdapter != null && mDataSetObserver != null) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
            mDataSetObserver = null;
        }

        mAdapter = adapter;

        if(mAdapter != null && mDataSetObserver == null) {
            mDataSetObserver = new AdapterDataSetObserver();
            mAdapter.registerDataSetObserver(mDataSetObserver);
        }
    }

    @Override
    public View getSelectedView() {
        return mActiveCard;
    }


    private class AdapterDataSetObserver extends DataSetObserver {
        @Override
        public void onInvalidated() {
            requestLayout();
        }

        @Override
        public void onChanged() {
            requestLayout();
        }
    }

}
