package com.dove.common.content;

import android.database.Cursor;

/**
 * An object that knows how to create its implementing class using a single row of a cursor alone.
 *
 * @param <T>
 */
public interface CursorCreator<T> {
    /**
     * Creates an object using the current row of the cursor given here. The implementation should not
     * advance/rewind the cursor, and is only allowed to read the existing row.
     *
     * @param cursor
     * @return a real object of the implementing class.
     */
    T createFromCursor(Cursor cursor);
}
