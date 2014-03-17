
package com.dove.common.content;

import android.content.ContentValues;

public interface IContentValues<T> {

    /**
     * @return A {@link ContentValues} from this object.
     */
    ContentValues createContentvalues();
}
