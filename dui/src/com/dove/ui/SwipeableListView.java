
package com.dove.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class SwipeableListView extends ListView implements SwipeCallback, OnScrollListener {
    private static final String TAG = SwipeableListView.class.getSimpleName();

    private boolean mScrolling;
    private boolean mEnableSwipe;
    private SwipeHelper mSwipeHelper;
    private SwipeListener mSwipeListener;

    /**
     * The listener that's used to listener the swipe operation.
     */
    public interface SwipeListener {
        /**
         * The swipe callback at the beginning of swiping.
         */
        public void onBeginSwipe();
    }

    public SwipeableListView(Context context) {
        super(context);
        init(context);
    }

    public SwipeableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SwipeableListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mEnableSwipe = true;
        mSwipeHelper = new SwipeHelper(context, this, SwipeHelper.HORIZONTAL);
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mSwipeHelper.onConfigurationChanged(getContext(), newConfig);
    }

    public void setSwipeListener(SwipeListener swipeListener) {
        mSwipeListener = swipeListener;
    }

    /**
     * Enable swipe gestures.
     */
    public void enableSwipe(boolean enable) {
        mEnableSwipe = enable;
    }

    /**
     * Whether the list view is scrolling.
     * 
     * @return true if the view is scrolling, false not.
     */
    public boolean isScrolling() {
        return mScrolling;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        mScrolling = scrollState != OnScrollListener.SCROLL_STATE_IDLE;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
            int totalItemCount) {
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final boolean res;
        if (mScrolling || !mEnableSwipe) {
            res = super.onInterceptTouchEvent(ev);
        } else {
            res = mSwipeHelper.onInterceptTouchEvent(ev) || super.onInterceptTouchEvent(ev);
        }
        Log.d(TAG, "onInterceptTouchEvent  " + ev.getAction() + " :=  " + res);
        return res;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final boolean res;
        if (mEnableSwipe) {
            res = mSwipeHelper.onTouchEvent(ev) || super.onTouchEvent(ev);
        } else {
            res = super.onTouchEvent(ev);
        }
        Log.d(TAG, "onTouchEvent " + ev.getAction() + " := " + res);
        return res;
    }

    @Override
    public View getChildAt(MotionEvent ev) {
        final int touchY = (int) ev.getY();
        final int size = getChildCount();
        for (int i = 0; i < size; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }
            if (touchY >= child.getTop() && touchY <= child.getBottom()) {
                return child;
            }
        }
        return null;
    }

    @Override
    public boolean canBeDimissed(SwipeableItemView swipeableItemView) {
        return swipeableItemView.canChildBeDismissed();
    }

    @Override
    public void onScroll() {
    }

    @Override
    public void onBeginDrag(View v) {
        // We do this so the underlying ScrollView knows that it won't get
        // the chance to intercept events anymore
        requestDisallowInterceptTouchEvent(true);

        if (mSwipeListener != null) {
            mSwipeListener.onBeginSwipe();
        }
    }

    @Override
    public void onDismissed(SwipeableItemView v) {
    }

    @Override
    public void onCancelled(SwipeableItemView v) {
    }

    @Override
    public SwipeableItemBehindView getSwipeableItemBehindView() {
        return null;
    }

}
