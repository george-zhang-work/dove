
package com.dove.reader.ui.controller;

import android.database.DataSetObservable;
import android.database.DataSetObserver;

public interface DrawerController {
    /**
     * Get the Drawer's {@link DataSetObservable}.
     * <p>
     * For a fragment, a drawer {@link DataSetObserver} may be need to be
     * registered when the fragment is created successfully, and to be
     * unregistered when the fragment is to be destroyed.
     * <p/>
     * When the drawer's state chagned, like opened or closed, the relative
     * fragment should be notified to do data change.
     */
    DataSetObservable getDrawerObserverable();
}
