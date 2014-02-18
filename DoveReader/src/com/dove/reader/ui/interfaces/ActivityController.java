
package com.dove.reader.ui.interfaces;

import android.os.Bundle;

/***
 * An Activity controller knows how to combine views and listeners into a
 * functioning activity. ActivityControllers are delegate that implement methods
 * by calling underlying views to modify or respond to user action.
 */
public interface ActivityController {

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
}
