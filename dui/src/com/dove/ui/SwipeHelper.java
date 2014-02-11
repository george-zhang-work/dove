
package com.dove.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.dove.dui.R;
import com.dove.log.LogUtils;

public class SwipeHelper {
    private static final String TAG = SwipeHelper.class.getName();
    private static final boolean DEBUG_INVALIDATE = true;
    private static final boolean LOG_SWIPE_DISMISS_VELOCITY = true;

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private static final int INVALIDATE_POSITION = -1;
    private static final boolean CONSTRAIN_SWIPE = true;
    private static final boolean FADE_OUT_DURING_SWIPE = true;
    private static final boolean DISMISS_IF_SWIPED_FAR_ENOUGH = true;

    /**
     * The fraction is used to check the scroll is enabled or not,
     */
    private static final float SCROLL_FACTOR = 1.2F;

    /**
     * fraction of thumbnail width where fade starts
     */
    private static final float ALPAH_FADE_START = 0f;
    /**
     * Fraction of thumbnail width beyond which alpha->0
     */
    private static final float ALPHA_FADE_END = 0.7f;

    private static final float ALPHA_TEXT_FADE_START = 0.4f;
    /**
     * Dead region where swipe cannot be initiated.
     */
    private static final int DEAD_REGION_FOR_SWIPE = 56;

    private static final DecelerateInterpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator(
            1.0f);

    private static float MIN_SWIPE;
    private static int SNAP_ANIM_LEN;
    private static int MAX_DISMISS_VELOCITY;
    private static int SWIPE_ESCAPE_VELOCITY = -1;
    private static int MAX_ESCAPE_ANIMATION_DURATION;
    private static int DEFAULT_ESCAPE_ANIMATION_DURATION;

    private final int mSwipeDirection;
    private final SwipeCallback mSwipeCallback;
    private final VelocityTracker mVelocityTracker;

    private boolean mDragging;
    private SwipeableItemView mCurItemView;
    private SwipeableItemBehindView mCurItemBehindView;

    private float mLastY;
    private float mDensityScale;
    private float mPagingTouchSlop;
    private float mInitialTouchPosX;
    private float mInitialTouchPosY;

    public SwipeHelper(Context context, SwipeCallback swipeCallback, int swipeDiection,
            float densityScale, float pagingTouchSlop) {
        mSwipeCallback = swipeCallback;
        mSwipeDirection = swipeDiection;
        mDensityScale = densityScale;
        mVelocityTracker = VelocityTracker.obtain();
        mPagingTouchSlop = pagingTouchSlop;

        if (SWIPE_ESCAPE_VELOCITY == -1) {
            Resources res = context.getResources();
            SWIPE_ESCAPE_VELOCITY = res.getInteger(R.integer.swipe_escape_velocity);
            DEFAULT_ESCAPE_ANIMATION_DURATION = res.getInteger(R.integer.escape_animation_duration);
            MAX_ESCAPE_ANIMATION_DURATION = res.getInteger(R.integer.max_escape_animation_duration);
            MAX_DISMISS_VELOCITY = res.getInteger(R.integer.max_dismiss_velocity);
            SNAP_ANIM_LEN = res.getInteger(R.integer.snap_animation_duration);
            MIN_SWIPE = res.getDimension(R.dimen.min_swipe);
        }
    }

    public void setDensityScale(float densityScale) {
        mDensityScale = densityScale;
    }

    public void setPagingTouchSlop(float pagingTouchSlop) {
        mPagingTouchSlop = pagingTouchSlop;
    }

    /**
     * @see ViewGroup#onInterceptTouchEvent(MotionEvent)
     */
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDragging = false;
                mLastY = ev.getY();
                View view = mSwipeCallback.getChildAt(ev);
                if (view instanceof SwipeableItemView) {
                    mCurItemView = (SwipeableItemView) view;
                }
                mVelocityTracker.clear();
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
                        float absDeltaX = Math.abs(ev.getX() - mInitialTouchPosX);
                        float absDeltaY = Math.abs(ev.getY() - mInitialTouchPosY);
                        if (absDeltaY > mCurItemView.getMinAllowScrollDistance()
                                && absDeltaY > SCROLL_FACTOR * absDeltaX) {
                            mLastY = ev.getY();
                            mSwipeCallback.onScroll();
                            return false;
                        }
                    }
                    mVelocityTracker.addMovement(ev);
                    float absDelta = Math.abs(ev.getX() - mInitialTouchPosX);
                    if (absDelta > mPagingTouchSlop) {
                        mDragging = true;
                        mCurItemBehindView = mSwipeCallback.getSwipeableItemBehindView();
                        View animView = mCurItemView.getSwipeableView().getView();
                        mSwipeCallback.onBeginDrag(animView);
                        mInitialTouchPosX = ev.getX() - animView.getTranslationX();
                        mInitialTouchPosY = ev.getY();

                    }
                }
                mLastY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                // Loop through.
            case MotionEvent.ACTION_CANCEL:
                mDragging = false;
                mCurItemView = null;
                mLastY = INVALIDATE_POSITION;
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
                    float deltaX = ev.getX() - mInitialTouchPosX;
                    // If the user has gone vertical and not gone horizontalis
                    // AT LEAT minBeforeLock, switch to scroll. otherwise,
                    // cancel the swipe.
                    if (Math.abs(deltaX) < MIN_SWIPE) {
                        // Don't start the drag until at least X distance has
                        // occurred.
                        return true;
                    }
                    boolean canBeDismissed = mSwipeCallback.canChildBeDimissed(mCurItemView);
                    // Don't let that can't be dismissed be dragged more than
                    // maxScrollDistance.
                    View animView = mCurItemView.getSwipeableView().getView();
                    if (CONSTRAIN_SWIPE && !canBeDismissed) {
                        float size = getSize(animView);
                        float maxScrollDistance = 0.15f * size;
                        if (Math.abs(deltaX) >= size) {
                            deltaX = deltaX > 0 ? maxScrollDistance : -maxScrollDistance;
                        } else {
                            deltaX = maxScrollDistance
                                    * (float) Math.sin((deltaX / size) * (Math.PI / 2));
                        }
                    }
                    setTranslation(animView, deltaX);
                    if (FADE_OUT_DURING_SWIPE && canBeDismissed) {
                        animView.setAlpha(getAlphaForOffset(animView));
                        if (mCurItemBehindView != null) {
                            mCurItemBehindView.setTextAlpha(getTextAlphaForOffset(animView));
                        }
                    }
                    invalidateGlobalRegion(animView);
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
                View animView = mCurItemView.getSwipeableView().getView();
                float translation = Math.abs(animView.getTranslationX());
                float curAnimViewSize = getSize(animView);
                // Long swipe = translation of .4 * width
                boolean childSwipedFarEnough = DISMISS_IF_SWIPED_FAR_ENOUGH
                        && translation > 0.4 * curAnimViewSize;
                // Fast swipe = > escapeVelocity and translation of .1 * width
                boolean childSwipedFastEnough = Math.abs(velocity) > escapeVelocity
                        && Math.abs(velocity) > Math.abs(perpendicularVelocity)
                        && (velocity > 0) == (animView.getTranslationX() > 0)
                        && translation > 0.05 * curAnimViewSize;
                if (LOG_SWIPE_DISMISS_VELOCITY) {
                    LogUtils.v(TAG, "Swipe/Dismiss: " + velocity + "/" + escapeVelocity + "/"
                            + perpendicularVelocity + ", x: " + translation + "/" + curAnimViewSize);
                }
                boolean dismissChild = mSwipeCallback.canChildBeDimissed(mCurItemView)
                        && (childSwipedFarEnough || childSwipedFastEnough);

                if (dismissChild) {
                    dismissChild(mCurItemView, childSwipedFastEnough ? velocity : 0f);
                } else {
                    snapChild(mCurItemView);
                }
                break;

        }
        return true;
    }

    private float getSize(View v) {
        return mSwipeDirection == HORIZONTAL ? v.getMeasuredWidth() : v.getMeasuredHeight();
    }

    private void setTranslation(View v, float translate) {
        if (mSwipeDirection == HORIZONTAL) {
            v.setTranslationX(translate);
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

    private void dismissChild(final SwipeableItemView swipeableView, float velocity) {
        final View animView = swipeableView.getSwipeableView().getView();
        final boolean canBeDismissed = mSwipeCallback.canChildBeDimissed(swipeableView);
        float newPos = determinePos(animView, velocity);
        int duration = determineDuration(animView, newPos, velocity);

        enableHardwareLayer(animView);

        ObjectAnimator anim = createDismissAnimation(animView, newPos, duration);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mSwipeCallback.onChildDismissed(swipeableView);
                animView.setLayerType(View.LAYER_TYPE_NONE, null);
            }
        });
        anim.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (FADE_OUT_DURING_SWIPE && canBeDismissed) {
                    animView.setAlpha(getAlphaForOffset(animView));
                }
                invalidateGlobalRegion(animView);
            }
        });
        anim.start();
    }

    private void snapChild(final SwipeableItemView swipeableView) {
        final View animView = swipeableView.getSwipeableView().getView();
        final boolean canBeDismissed = mSwipeCallback.canChildBeDimissed(swipeableView);
        final int duration = SNAP_ANIM_LEN;

        ObjectAnimator anim = createTranslationAnimation(animView, 0, duration);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animView.setAlpha(1.0f);
                mSwipeCallback.onDragCancelled(swipeableView);
            }
        });
        anim.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (FADE_OUT_DURING_SWIPE && canBeDismissed) {
                    animView.setAlpha(getAlphaForOffset(animView));
                }
                invalidateGlobalRegion(animView);
            }
        });
        anim.start();
    }

    private float determinePos(View animView, float velocity) {
        final float newPos;
        if (velocity < 0
                || (velocity == 0 && animView.getTranslationX() < 0)
                || (velocity == 0 && animView.getTranslationX() == 0 && mSwipeDirection == VERTICAL)) {
            newPos = -getSize(animView);
        } else {
            newPos = getSize(animView);
        }
        return newPos;
    }

    private static int determineDuration(View animView, float newPos, float velocity) {
        int duration = MAX_ESCAPE_ANIMATION_DURATION;
        if (velocity != 0) {
            duration = Math.min(duration,
                    (int) (Math.abs(newPos - animView.getTranslationX()) * 1000f / Math
                            .abs(velocity)));
        } else {
            duration = DEFAULT_ESCAPE_ANIMATION_DURATION;
        }
        return duration;
    }

    private ObjectAnimator createDismissAnimation(View v, float newPos, int duration) {
        ObjectAnimator anim = createTranslationAnimation(v, newPos, duration);
        anim.setInterpolator(DECELERATE_INTERPOLATOR);
        return anim;
    }

    private ObjectAnimator createTranslationAnimation(View v, float newPos, int duration) {
        String direction = mSwipeDirection == HORIZONTAL ? "translationX" : "translationY";
        ObjectAnimator anim = ObjectAnimator.ofFloat(v, direction, newPos);
        anim.setDuration(duration);
        return anim;
    }

    /**
     * Sets the layer type of a view to hardware if the view is attached and
     * hardware acceleration is enabled. Does nothing otherwise.
     */
    private static void enableHardwareLayer(View v) {
        if (v != null && v.isHardwareAccelerated()) {
            v.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            v.buildLayer();
        }
    }

    private float getAlphaForOffset(View view) {
        float viewSize = getSize(view);
        final float fadeSize = ALPHA_FADE_END * viewSize;
        float result = 1.0f;
        float pos = view.getTranslationX();
        if (pos >= viewSize * ALPAH_FADE_START) {
            result = 1.0f - (pos - viewSize * ALPAH_FADE_START) / fadeSize;
        } else if (pos < viewSize * (1.0f - ALPAH_FADE_START)) {
            result = 1.0f + (viewSize * ALPAH_FADE_START + pos) / fadeSize;
        }
        float minAlpha = 0.5f;
        return Math.max(minAlpha, result);
    }

    private float getTextAlphaForOffset(View view) {
        float viewSize = getSize(view);
        final float fadeSize = ALPHA_TEXT_FADE_START * viewSize;
        float result = 1.0f;
        float pos = view.getTranslationX();
        if (pos >= 0) {
            result = 1.0f - pos / fadeSize;
        } else if (pos < 0) {
            result = 1.0f + pos / fadeSize;
        }
        return Math.max(0, result);
    }

    private static void invalidateGlobalRegion(View view) {
        invalidateGlobalRegion(view,
                new RectF(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
    }

    private static void invalidateGlobalRegion(View view, RectF childBounds) {
        if (DEBUG_INVALIDATE) {
            LogUtils.v(TAG, "---------------");
        }
        while (view.getParent() != null && view.getParent() instanceof View) {
            view = (View) view.getParent();
            view.getMatrix().mapRect(childBounds);
            view.invalidate((int) Math.floor(childBounds.left), (int) Math.floor(childBounds.top),
                    (int) Math.ceil(childBounds.right), (int) Math.ceil(childBounds.bottom));
            if (DEBUG_INVALIDATE) {
                LogUtils.v(
                        TAG,
                        "INVALIDATE(" + (int) Math.floor(childBounds.left) + ","
                                + (int) Math.floor(childBounds.top) + ","
                                + (int) Math.ceil(childBounds.right) + ","
                                + (int) Math.ceil(childBounds.bottom));
            }
        }
    }
}
