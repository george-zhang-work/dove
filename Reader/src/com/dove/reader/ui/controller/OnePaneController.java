
package com.dove.reader.ui.controller;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.dove.reader.R;
import com.dove.reader.ui.ReaderActivity;
import com.dove.reader.ui.ViewMode;

/**
 * Controller for one-pane Reader activity. One Pane is used for phones, where
 * screen real estate is limited. This controller also does the layout, since
 * the layout is simpler in the one pane case.
 */
public class OnePaneController extends AbstractActivityController {

    public OnePaneController(ReaderActivity activity, ViewMode viewMode) {
        super(activity, viewMode);
    }

    @Override
    public boolean onCreate(Bundle savedState) {
        mActivity.setContentView(R.layout.one_pane_activity);
        mDrawerContainer = (DrawerLayout) mActivity.findViewById(R.id.drawer_container);
        mDrawerPullOut = mActivity.findViewById(R.id.drawer_pull_out);
        mDrawerPullOut.setBackgroundResource(R.color.list_background_color);
        return super.onCreate(savedState);
    }

}
