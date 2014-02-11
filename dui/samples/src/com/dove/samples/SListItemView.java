
package com.dove.samples;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.dove.ui.SwipeableItemView;

public class SListItemView extends FrameLayout implements SwipeableItemView {
    private final static String TAG = SListItemView.class.getSimpleName();

    public SListItemView(Context context) {
        super(context);
        init(context);
    }

    public SListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SListItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        addView(View.inflate(context, R.layout.slv_item, null));
    }

    @Override
    public float getMinAllowScrollDistance() {
        return 150;
    }

    @Override
    public SwipeableView getSwipeableView() {
        return SwipeableView.from(this);
    }

    @Override
    public boolean canChildBeDismissed() {
        return true;
    }

    @Override
    public void dimiss() {
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final boolean res = super.onInterceptTouchEvent(ev);
        Log.d(TAG, "onInterceptTouchEvent " + ev.getAction() + " := " + res);
        return res;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        final boolean res = super.onTouchEvent(ev);
        Log.d(TAG, "onTouchEvent " + ev.getAction() + " := " + res);
        return true;
    }
}
