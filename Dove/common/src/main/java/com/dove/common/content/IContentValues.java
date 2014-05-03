package com.dove.common.content;

import android.content.ContentValues;

public interface IContentValues<T> {

    /**
     * @return A {@link android.content.ContentValues} from this object.
     */
    ContentValues createContentvalues();
}
