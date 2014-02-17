
package com.dove.reader.ui.controller;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.dove.reader.ui.ReaderActivity;
import com.dove.reader.ui.ViewMode;
import com.dove.reader.ui.interfaces.ActivityController;
import com.dove.reader.ui.interfaces.ControllableActivity;

/**
 * This is an abstract implemention of the Activity Controller. This class knows
 * how to respond to menu items, state changes, layout changes, etc. It weaves
 * together the views and listeners, dispatching action to the respective
 * underlying classes.
 * <p>
 * Even through this class is abstract, it should provide defualt implementions
 * for most, if not all the methods in the {@link ActivityController} interface.
 * This makes the task of subclasses easier: {@link OnePaneController} and
 * {@link TwoPaneController}, can be concise when the common functionality is in
 * {@link AbstractActivityController}.
 * <p>
 */
public abstract class AbstractActivityController implements ActivityController {

    protected final Context mContext;
    protected final ControllableActivity mActivity;
    protected final FragmentManager mFragmentManager;

    protected DrawerLayout mDrawerContainer;
    protected View mDrawerPullOut;

    /**
     * The current mode of the application. All changes in mode are initiated by
     * the activity controller. View mode changes are propagated to classes that
     * attach themselves as listeners of view mode chagnes.
     */
    protected final ViewMode mViewMode;

    public AbstractActivityController(ReaderActivity activity, ViewMode viewMode) {
        mActivity = activity;
        mFragmentManager = activity.getFragmentManager();
        mContext = activity.getApplicationContext();
        mViewMode = viewMode;
    }

    @Override
    public boolean onCreate(Bundle savedState) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Initialize the action bar. This is not visible to OnePaneController and
     * TwoPaneController, so they cannot override this behavior.
     */
    private void initializeActionBar() {
        final ActionBar actionBar = mActivity.getActionBar();
        if (actionBar == null) {
            return;
        }
    }
}
