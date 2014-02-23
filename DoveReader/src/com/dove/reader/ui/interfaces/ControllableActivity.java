
package com.dove.reader.ui.interfaces;

import com.dove.reader.ui.controller.DrawerController;
import com.dove.reader.ui.controller.FolderController;

/**
 * A controllable activity is an Activity that has a Controller attached. This
 * activity must be able to attach the various view fragments and delegate the
 * method calls between them.
 */
public interface ControllableActivity extends RestrictedActivity {
    /**
     * @return The {@link DrawerController} object associated with this
     *         activity, if any.
     */
    DrawerController getDrawerController();

    /**
     * @return The {@link FolderController} object associated with this
     *         activity, if any.
     */
    FolderController getFolderController();

}
