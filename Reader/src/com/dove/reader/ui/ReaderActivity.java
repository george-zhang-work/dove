
package com.dove.reader.ui;

import android.os.Bundle;

import com.dove.reader.ui.controller.AccountController;
import com.dove.reader.ui.controller.ControllerFactory;
import com.dove.reader.ui.controller.DrawerController;
import com.dove.reader.ui.controller.FolderController;
import com.dove.reader.ui.interfaces.ActivityController;
import com.dove.reader.ui.interfaces.ControllableActivity;
import com.dove.reader.utils.Utils;

/**
 * This is the root activity container that holds the left navigation fragment,
 * and the main content fragment.
 */
public class ReaderActivity extends AbstractReaderActivity implements ControllableActivity {

    private ViewMode mViewMode;

    /**
     * To which deletegate most Activity lifecycle.
     */
    private ActivityController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMode = new ViewMode();
        final boolean tabletUi = Utils.useTabletUI(getResources());
        mController = ControllerFactory.forActivity(this, mViewMode, tabletUi);
        mController.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mController.onPostCreate(savedInstanceState);
    }

    @Override
    public AccountController getAccountController() {
        return mController;
    }

    @Override
    public DrawerController getDrawerController() {
        return mController;
    }

    @Override
    public FolderController getFolderController() {
        return mController;
    }

}
