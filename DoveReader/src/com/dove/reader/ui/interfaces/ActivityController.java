
package com.dove.reader.ui.interfaces;

import android.content.res.Configuration;
import android.os.Bundle;

import com.dove.reader.ui.controller.DrawerController;
import com.dove.reader.ui.controller.FolderController;

/***
 * An Activity controller knows how to combine views and listeners into a
 * functioning activity. ActivityControllers are delegate that implement methods
 * by calling underlying views to modify or respond to user action.
 */
public interface ActivityController extends FolderController, DrawerController {

    /**
     * Called when the root activity calls onCreate. Any initialization needs to
     * be done here. Subclass need to call their parent's onCreat method, since
     * it performs valuable initialization common to call subclasses.
     * <p>
     * This was called initialize in Reader.
     * 
     * @param savedState Previous saved activity instance state, mybe null.
     * @return true if successfully create, false otherwise.
     * @see android.app.Activity#onCreate
     */
    boolean onCreate(Bundle savedState);

    /**
     * @see android.app.Activity#onPostCreate
     */
    void onPostCreate(Bundle savedState);

    /**
     * @see android.app.Activity#onConfigurationChanged
     */
    void onConfigurationChanged(Configuration newConfig);
}
