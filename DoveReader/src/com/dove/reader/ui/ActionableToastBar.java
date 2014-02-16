
package com.dove.reader.ui;

import android.animation.Animator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dove.reader.R;

/**
 * A custom view that exposes an action to the user.
 */
public class ActionableToastBar extends LinearLayout {
    private boolean mHidden = false;
    private Animator mShowAnimator;
    private Animator mHideAnimator;
    private final Runnable mRunnable;
    private final Handler mFadeOutHandler;

    /** Icon for the description. */
    private ImageView mActionDescriptionIcon;
    /** The clickable view. */
    private View mActionButton;
    /** Icon for the action button. */
    private View mActionIcon;
    /** The view that contains the description. */
    private TextView mActionDescriptionView;
    /** The view that contains the text for the action button. */
    private TextView mActionText;

    public ActionableToastBar(Context context) {
        this(context, null);
    }

    public ActionableToastBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionableToastBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mFadeOutHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                if (!mHidden) {
                    hide(true, false);
                }
            }
        };
        View.inflate(context, R.layout.actionable_toast_row, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    /**
     * Hide the view and resets the state.
     * 
     * @param animate
     * @param actionClicked
     */
    public void hide(boolean animate, boolean actionClicked) {
        mHidden = true;
        mFadeOutHandler.removeCallbacks(mRunnable);
        if (getVisibility() == View.VISIBLE) {

        }
    }
}
