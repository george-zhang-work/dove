
package com.dove.reader.utils;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.os.Build;

import com.dove.reader.R;

public class Utils {

    public static boolean hasIceCreatSandwich() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    @TargetApi(value = 16)
    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

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
