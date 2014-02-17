
package com.dove.reader.ui.controller;

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

}
