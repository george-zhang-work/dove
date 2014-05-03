package com.dove.reader.ui.controller;

import android.database.DataSetObservable;

/**
 * Controller for a account contained in a cannoical activity.
 */
public interface AccountController {
    /**
     * Get the account's {@link android.database.DataSetObservable}.
     * <em>
     * For a fragment, a folder {@link android.database.DataSetObserver} maybe need to be registered
     * when the fragment is created successfully, and to be unregistered when the fragment is to be destroyed.
     * </em>
     */
    DataSetObservable getAccountObservable();

}
