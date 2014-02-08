
package com.dove.ui;

import android.view.MotionEvent;
import android.view.View;

public interface SwipeCallback {
    /**
     * Returns the view at the specified position in the group.
     * 
     * @param ev The motion event that triggered at the view.
     * @return the view at the specified position or null if the position does
     *         not exist within the group.
     */
    View getChildAt(MotionEvent ev);

    /**
     * To check whether the view could be dismissed.
     * 
     * @param swipeableItemView The view to be checked.
     * @return true if the view could be dismissed, false otherwise.
     */
    boolean canChildBeDimissed(SwipeableItemView swipeableItemView);

    /**
     * The current gesture detects a scroll operation.
     */
    void onScroll();

    /**
     * Start to drag the view.
     * 
     * @param v The view to be dragged.
     */
    void onBeginDrag(View v);
}
