
package com.dove.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class SwipeableListView extends ListView {

    public SwipeableListView(Context context) {
        super(context);
    }

    public SwipeableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeableListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        return super.onInterceptHoverEvent(event);
    }
    
    
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        return super.onTouchEvent(ev);
    }
}
