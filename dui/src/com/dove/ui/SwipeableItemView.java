
package com.dove.ui;

import android.view.View;

/**
 * Represents an item that can be dismissed by the SwipeableListView.
 */
public interface SwipeableItemView {
    public static class SwipeableView {
        public static SwipeableView from(View view) {
            view.setClickable(true);
            return new SwipeableView(view);
        }

        private final View mView;

        private SwipeableView(View view) {
            mView = view;
        }

        public View getView() {
            return mView;
        }
    }

    /**
     * Returns the minimum allowed displacement in the Y axis that is considered
     * a scroll. After this displacement, all future events are considered
     * scroll event rather than swipes.
     */
    public float getMinAllowScrollDistance();

    /**
     * Get the swiped view when swipe operation is operated.
     * 
     * @return The swipe view.
     */
    public SwipeableView getSwipeableView();

    /**
     * To check whether the child could be dismissed by swipe operation.
     * 
     * @return True is the child could be dismissed, otherwise false.
     */
    public boolean canChildBeDismissed();

    /**
     * Callback to be invoked when the child is dismissed.
     */
    public void dimiss();
}
