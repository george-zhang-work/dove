
package com.dove.reader.ui.actionbar;

import android.app.ActionBar;
import android.app.Notification.Action;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.dove.reader.ui.interfaces.ActivityController;
import com.dove.reader.ui.interfaces.ControllableActivity;

public class ReaderActionBarView extends LinearLayout {

    protected ActionBar mActionBar;
    protected ControllableActivity mActivity;
    protected ActivityController mController;

    public ReaderActionBarView(Context context) {
        this(context, null);
    }

    public ReaderActionBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ReaderActionBarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Initialize the action bar view.
     */
    public void initialize(ControllableActivity activity, ActivityController controller,
            ActionBar actionBar) {
        mActivity = activity;
        mController = controller;
        mActionBar = actionBar;
    }

    /**
     * Init the action bar to allow the 'up' affordance.
     */
    public void setBackButton() {
        if (mActionBar == null) {
            return;
        }
        // Show home as up, and show an icon.
        final int mask = ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_HOME;
        mActionBar.setDisplayOptions(mask, mask);
        mActivity.getActionBar().setHomeButtonEnabled(true);
    }
}
