
package com.dove.reader.utils;

import android.content.res.Resources;

import com.dove.reader.R;

public class Utils {

    /**
     * To check whether the tablet UI should be shown.
     * 
     * @param res The context resources.
     * @return ture if the tablet UI should be shown, otherwise false.
     */
    public static boolean useTabletUI(Resources res) {
        return res.getInteger(R.integer.use_tablet_ui) != 0;
    }
}
