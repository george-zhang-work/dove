
package com.dove.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.dove.dui.R;

public class SwipeHelper {

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private static final boolean CONSTRAIN_SWIPE = true;
    private static final boolean FADE_OUT_DURATION_SWIPE = true;
    private static final boolean DISMISS_IF_SWIPED_FAR_ENOUGH = true;
    /**
     * The fraction is used to check the scroll is enabled or not,
     */
    private static final float SCROLL_FACTOR = 1.2F;

    /**
     * Dead region where swipe cannot be initiated.
     */
    private static final int DEAD_REGION_FOR_SWIPE = 56;
    private static final DecelerateInterpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator(
            1.0f);

    private static int SWIPE_ESCAPE_VELOCITY = -1;
    private static float MIN_SWIPE;
    private static int MAX_DISMISS_VELOCITY;

    private final int mSwipeDirection;
    private final float mDensityScale;

    private final SwipeCallback mSwipeCallback;
    private final VelocityTracker mVelocityTracker;

    private boolean mDragging;
    private SwipeableItemView mCurItemView;

    private float mLastY;
    private float mPagingTouchSlop;
    private float mInitialTouchPosX;
    private float mInitialTouchPosY;

    public SwipeHelper(Context context, SwipeCallback swipeCallback, int swipeDiection,
            int denistyScale, float pagingTouchSlop) {
        mSwipeCallback = swipeCallback;
        mSwipeDirection = swipeDiection;
        mDensityScale = denistyScale;
        mVelocityTracker = VelocityTracker.obtain();
        mPagingTouchSlop = pagingTouchSlop;

        if (SWIPE_ESCAPE_VELOCITY == -1) {
            Resources res = context.getResources();
            // MIN_SWIPE = res.getDimension(R)
        }
    }

    /**
     * @see ViewGroup#onInterceptTouchEvent(MotionEvent)
     */
    public boolean onInterceptHoverEvent(MotionEvent ev) {
        final int action = ev.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDragging = false;
                mLastY = ev.getY();
                mVelocityTracker.clear();
                View view = mSwipeCallback.getChildAt(ev);
                if (view instanceof SwipeableItemView) {
                    mCurItemView = (SwipeableItemView) view;
                }
                if (mCurItemView != null) {
                    mVelocityTracker.addMovement(ev);
                    mInitialTouchPosX = ev.getX();
                    mInitialTouchPosY = ev.getY();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mCurItemView != null) {
                    // Check the movement direction.
                    if (mLastY >= 0 && !mDragging) {
                        float deltaX = Math.abs(ev.getX() - mInitialTouchPosX);
                        float deltaY = Math.abs(ev.getY() - mInitialTouchPosY);
                        if (deltaY > mCurItemView.getMinAllowScrollDistance()
                                && deltaY > SCROLL_FACTOR * deltaX) {
                            mLastY = ev.getY();
                            mSwipeCallback.onScroll();
                            return false;
                        }
                    }
                    mVelocityTracker.addMovement(ev);
                    float delta = Math.abs(ev.getX() - mInitialTouchPosX);
                    if (delta > mPagingTouchSlop) {
                        mDragging = true;
                        View v = mCurItemView.getSwipeableView().getView();
                        mSwipeCallback.onBeginDrag(v);
                        mInitialTouchPosX = ev.getX() - v.getTranslationX();
                        mInitialTouchPosY = ev.getY() - ev.getY();
                    }
                }
                mLastY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                // Loop through.
            case MotionEvent.ACTION_CANCEL:
                mDragging = false;
                mCurItemView = null;
                mLastY = -1;
                break;
        }
        return mDragging;
    }

    /**
     * @see ViewGroup#onTouchEvent(MotionEvent)
     */
    public boolean onTouchEvent(MotionEvent ev) {
        if (!mDragging) {
            return false;
        }
        mVelocityTracker.addMovement(ev);
        final int action = ev.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_OUTSIDE:
                // Loop through
            case MotionEvent.ACTION_MOVE:
                if (mCurItemView != null) {
                    // If the swipe started in the dead region, ignore it.
                    if (mInitialTouchPosX <= DEAD_REGION_FOR_SWIPE * mDensityScale) {
                        return true;
                    }
                    // If the user has gone vertical and not gone horizontalis
                    // AT LEAT minBeforeLock, switch to scroll. otherwise,
                    // cancel the swipe.
                    float deltaX = ev.getX() - mInitialTouchPosX;
                    if (Math.abs(deltaX) < MIN_SWIPE) {
                        // Don't start the drag until at least X distance has
                        // occurred.
                        return true;
                    }
                    // Don't let that can't be dismissed be dragged more than
                    // maxScrollDistance.
                    View v = mCurItemView.getSwipeableView().getView();
                    if (CONSTRAIN_SWIPE && !mSwipeCallback.canChildBeDimissed(mCurItemView)) {
                        float size = getSize(v);
                        float maxScrollDistance = 0.15f * size;
                        if (Math.abs(deltaX) >= size) {
                            deltaX = deltaX > 0 ? maxScrollDistance : -maxScrollDistance;
                        } else {
                            deltaX = maxScrollDistance
                                    * (float) Math.sin((deltaX / size) * (Math.PI / 2));
                        }
                    }
                    setTranslation(v, deltaX);
                }
                break;
            case MotionEvent.ACTION_UP:
                // Loop through.
            case MotionEvent.ACTION_CANCEL:
                float maxVelocity = MAX_DISMISS_VELOCITY * mDensityScale;
                mVelocityTracker.computeCurrentVelocity(1000 /* px/sec */, maxVelocity);
                float escapeVelocity = SWIPE_ESCAPE_VELOCITY * mDensityScale;
                float velocity = getVelocity(mVelocityTracker);
                float perpendicularVelocity = getPerpendicularVelocity(mVelocityTracker);

                // Decide whether to dismiss the current view
                // Tweak constants below as required to prevent erroneous
                // swipe/dismiss
                View v = mCurItemView.getSwipeableView().getView();
                float translation = Math.abs(v.getTranslationX());
                float curAnimViewSize = getSize(v);
                // Long swipe = translation of .4 * width
                boolean childSwipedFarEnough = DISMISS_IF_SWIPED_FAR_ENOUGH
                        && translation > 0.4 * curAnimViewSize;
                break;

        }
        return false;
    }

    private float getSize(View v) {
        return mSwipeDirection == HORIZONTAL ? v.getMeasuredWidth() : v.getMeasuredHeight();
    }

    private void setTranslation(View v, float translate) {
        if (mSwipeDirection == HORIZONTAL) {
            v.setRotationX(translate);
        } else {
            v.setTranslationY(translate);
        }
    }

    private float getVelocity(VelocityTracker vt) {
        return mSwipeDirection == HORIZONTAL ? vt.getXVelocity() : vt.getYVelocity();
    }

    private float getPerpendicularVelocity(VelocityTracker vt) {
        return mSwipeDirection == HORIZONTAL ? vt.getYVelocity() : vt.getXVelocity();
    }

    private ObjectAnimator createTranslationAnimation(View v, float newPos) {
        String direction = mSwipeDirection == HORIZONTAL ? "translationX" : "translationY";
        return ObjectAnimator.ofFloat(v, direction, newPos);
    }

    private ObjectAnimator createDismissAnimation(View v, float newPos, int duration) {
        ObjectAnimator anim = createTranslationAnimation(v, newPos);
        anim.setInterpolator(DECELERATE_INTERPOLATOR);
        anim.setDuration(duration);
        return anim;
    }
}
