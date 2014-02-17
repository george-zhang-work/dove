
package com.dove.reader.ui.controller;

import com.dove.reader.ui.ReaderActivity;
import com.dove.reader.ui.ViewMode;
import com.dove.reader.ui.interfaces.ActivityController;

/**
 * Controller for tow-pane Reader activity. Two pane is used for tablets, where
 * screen real estate abounds.
 */
public class TwoPaneController extends AbstractActivityController {

    public TwoPaneController(ReaderActivity activity, ViewMode viewMode) {
        super(activity, viewMode);
    }

}
