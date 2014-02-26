
package com.dove.reader.utils;

import android.net.Uri;
import android.text.TextUtils;

public final class UriUtils {
    /**
     * Returns true if Uri lfs, ignoring any query parameters and fragment, and
     * Uri rhs, ignoring any query parameters, are equal, including if they are
     * both null.
     * 
     * @param lfs The left uri to comapre.
     * @param rhs The right uri to be compared.
     * @return true if lfs and rhs are equal, otherwise false.
     */
    public static boolean equalsIgnoreQueryFragment(Uri lfs, Uri rhs) {
        if (lfs == rhs) {
            return true;
        }
        if (lfs == null || rhs == null || !TextUtils.equals(lfs.getScheme(), rhs.getScheme())
                || !TextUtils.equals(lfs.getAuthority(), rhs.getAuthority())
                || !TextUtils.equals(lfs.getEncodedPath(), rhs.getEncodedPath())) {
            return false;
        }
        return true;
    }
}
