package com.dove.reader.ui.controller;

import android.database.DataSetObservable;

public interface DrawerController {
    /**
     * Get the Drawer's {@link DataSetObservable}.
     * <em>
     * For a fragment, a drawer {@link android.database.DataSetObserver} maybe need to be registered
     * when the fragment is created successfully, and to be unregistered when the fragment is to be destroyed.
     * </em>
     * When the drawer's state changed, like opened or closed, the relative fragment should be notified to do data change.
     */
    DataSetObservable getDrawerObservable();
}
