
package com.dove.alarm;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class AlarmProvider extends ContentProvider {

    private static final int ALARMS = 0x01;
    private static final int ALARMS_ID = 0x02;

    private static final UriMatcher sURLMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURLMatcher.addURI(AlarmContract.AUTHORITY, "alarms", ALARMS);
        sURLMatcher.addURI(AlarmContract.AUTHORITY, "alarms/#", ALARMS_ID);
    }

    private AlarmDatabaseHelper mOpenHelper;

    @Override
    public boolean onCreate() {
        mOpenHelper = new AlarmDatabaseHelper(getContext(), null);
        return true;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sURLMatcher.match(uri);
        switch (match) {
            case ALARMS:
                return "vnd.android.cursor.dir/alarms";
            case ALARMS_ID:
                return "vnd.android.cursor.item/alarms";
            default:
                throw new IllegalArgumentException("Unkonw URL");
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

}
