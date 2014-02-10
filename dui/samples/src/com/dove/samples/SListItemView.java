
package com.dove.samples;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.dove.ui.SwipeableItemView;

public class SListItemView extends FrameLayout implements SwipeableItemView {

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
}
