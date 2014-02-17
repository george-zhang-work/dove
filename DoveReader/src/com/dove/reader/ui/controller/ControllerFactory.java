
package com.dove.reader.ui.controller;

import com.dove.reader.ui.ReaderActivity;
import com.dove.reader.ui.ViewMode;
import com.dove.reader.ui.interfaces.ActivityController;

/**
 * Create the appropriate ActivityController to control {@link ReaderActivity}.
 */
public class ControllerFactory {
    public static ActivityController forActivity(ReaderActivity activity, ViewMode viewMode,
            boolean isTabletDevice) {
        return isTabletDevice ? new TwoPaneController() : new OnePaneController();
    }
}
