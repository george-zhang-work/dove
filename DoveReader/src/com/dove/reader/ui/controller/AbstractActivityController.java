
package com.dove.reader.ui.controller;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.DataSetObservable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import com.dove.reader.R;
import com.dove.reader.ui.ReaderActivity;
import com.dove.reader.ui.ViewMode;
import com.dove.reader.ui.actionbar.ReaderActionBarView;
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

    protected View mDrawerPullOut;
    protected DrawerLayout mDrawerContainer;
    protected ActionBarDrawerToggle mDrawerToggle;
    protected ReaderDrawerListener mDrawerListener;

    protected ReaderActionBarView mActionBarView;
    /**
     * The current mode of the application. All changes in mode are initiated by
     * the activity controller. View mode changes are propagated to classes that
     * attach themselves as listeners of view mode chagnes.
     */
    protected final ViewMode mViewMode;

    /**
     * Listeners that are interested in changes to the current folder.
     */
    private final DataSetObservable mFolderObservable = new DataSetObservable();

    /**
     * Listeners that are interested in changes to the drawer state..
     */
    private final DataSetObservable mDrawerObservable = new DataSetObservable();

    public AbstractActivityController(ReaderActivity activity, ViewMode viewMode) {
        mActivity = activity;
        mFragmentManager = activity.getFragmentManager();
        mContext = activity.getApplicationContext();
        mViewMode = viewMode;
    }

    @Override
    public boolean onCreate(Bundle savedState) {
        initializeActionBar();
        // Allow shortcut keys to function for the ActionBar and menus.
        mActivity.setDefaultKeyMode(Activity.DEFAULT_KEYS_SHORTCUT);

        // The "open drawer description" argument is for when the drawer is
        // open, so tell the user that interaction will cause the drawer to
        // close and vice versa for the "close drawer description" argument.
        mDrawerToggle = new ActionBarDrawerToggle((Activity) mActivity, mDrawerContainer,
                R.drawable.ic_drawer, R.string.drawer_close, R.string.drawer_open);
        mDrawerListener = new ReaderDrawerListener();
        mDrawerContainer.setDrawerListener(mDrawerListener);
        mDrawerContainer.setDrawerShadow(
                mContext.getResources().getDrawable(R.drawable.drawer_shadow), Gravity.START);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        return true;
    }

    @Override
    public void onPostCreate(Bundle savedState) {
        // Sync the toggle state after onRestoreInstanceState has occured.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        mDrawerToggle.onConfigurationChanged(newConfig);
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
        // Be sure to inherit from the Actionbar theme when inflating.
        final LayoutInflater inflater = LayoutInflater.from(actionBar.getThemedContext());
        final boolean isSearch = mActivity.getIntent() != null
                && Intent.ACTION_SEARCH.equals(mActivity.getIntent().getAction());
        mActionBarView = (ReaderActionBarView) inflater.inflate(
                isSearch ? R.layout.search_actionbar_view : R.layout.actionbar_view, null);
        mActionBarView.initialize(mActivity, this, actionBar);

        // Init the action bar to allow the 'up' affordance.
        // Any configuration that disallow 'up' should do that later.
        mActionBarView.setBackButton();
    }

    private class ReaderDrawerListener implements DrawerLayout.DrawerListener {
        private int mDrawerState;
        private float mOldSlideOffset;

        public ReaderDrawerListener() {
            mDrawerState = DrawerLayout.STATE_IDLE;
            mOldSlideOffset = 0.f;
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            mDrawerToggle.onDrawerOpened(drawerView);
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            mDrawerToggle.onDrawerClosed(drawerView);
            // TODO Try to update drawer data if necessary.
        }

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
            mOldSlideOffset = slideOffset;

            // Always try to show the burger if sliding.
            mDrawerToggle.setDrawerIndicatorEnabled(true /* enable */);
        }

        /**
         * This condition here should only be called when the drawer is stuck in
         * a weird state and doesn't register the onDrawerClosed, but shows up
         * as idle. Make sure to refresh and, more importantly, unlock the
         * drawer when this is the case.
         */
        @Override
        public void onDrawerStateChanged(int newState) {
            mDrawerState = newState;
            mDrawerToggle.onDrawerStateChanged(newState);
        }
    }

    @Override
    public DataSetObservable getDrawerObserverable() {
        return mDrawerObservable;
    }

    @Override
    public DataSetObservable getFolderObserverable() {
        return mFolderObservable;
    }
}
