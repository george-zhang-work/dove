
package com.dove.reader.content;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;

/**
 * This class main work is to parser the cursor result into object that stored
 * in {@link ObjectCursor}. Here, the reason to override the
 * {@link #loadInBackground()} is that, the parser work maybe a time consuming
 * work.
 * 
 * @param <T> the data type to be loaded.
 */
public class ObjectCursorLoader<T> extends CursorLoader {

    /**
     * The factory that knows how to create T objects.
     */
    private final CursorCreator<T> mFactory;

    public ObjectCursorLoader(Context context, CursorCreator<T> factory) {
        super(context);
        mFactory = factory;
    }

    public ObjectCursorLoader(Context context, CursorCreator<T> factory, Uri uri,
            String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        super(context, uri, projection, selection, selectionArgs, sortOrder);
        mFactory = factory;
    }

    @Override
    public Cursor loadInBackground() {
        Cursor cursor = super.loadInBackground();
        if (cursor != null && !(cursor instanceof ObjectCursor<?>)) {
            if (mFactory != null) {
                ObjectCursor<T> objectCursor = new ObjectCursor<T>(cursor, mFactory);
                objectCursor.fillCache();
                return objectCursor;
            }
        }
        return cursor;
    }
}
