
package com.dove.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;

public class SwipeableListView extends ListView implements SwipeCallback, OnScrollListener {

    private SwipeHelper mSwipeHelper;
    private boolean mEnableSwipe;
    private boolean mScrolling;

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
        float densityScale = getResources().getDisplayMetrics().density;
        float pagingTouchSlop = ViewConfiguration.get(context).getScaledPagingTouchSlop();
        mSwipeHelper = new SwipeHelper(context, this, SwipeHelper.HORIZONTAL, densityScale,
                pagingTouchSlop);
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        float densityScale = getResources().getDisplayMetrics().density;
        mSwipeHelper.setDensityScale(densityScale);
        float pagingTouchSlop = ViewConfiguration.get(getContext()).getScaledPagingTouchSlop();
        mSwipeHelper.setPagingTouchSlop(pagingTouchSlop);
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
    public boolean onInterceptHoverEvent(MotionEvent event) {
        if (mScrolling || !mEnableSwipe) {
            return super.onInterceptHoverEvent(event);
        } else {
            return mSwipeHelper.onInterceptHoverEvent(event) || super.onInterceptHoverEvent(event);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mEnableSwipe) {
            return mSwipeHelper.onTouchEvent(ev) || super.onTouchEvent(ev);
        } else {
            return super.onTouchEvent(ev);
        }
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
    public boolean canChildBeDimissed(SwipeableItemView swipeableItemView) {
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
    }

    @Override
    public void onChildDismissed(SwipeableItemView v) {
    }

    @Override
    public void onDragCancelled(SwipeableItemView v) {
    }

    @Override
    public SwipeableItemBehindView getSwipeableItemBehindView() {
        return null;
    }

}
