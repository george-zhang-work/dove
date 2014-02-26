
package com.dove.reader.ui.controller;

import android.database.DataSetObservable;
import android.database.DataSetObserver;

/**
 * Controller for a account contained in a cannoical activity.
 */
public interface AccountController {

    /**
     * Get the account's {@link DataSetObservable}. For a fragment, a folder
     * {@link DataSetObserver} may be need to be registered when the fragment is
     * created successfully, and to be unregistered when the fragment is to be
     * destroyed.
     */
    DataSetObservable getAccountObservable();

}
