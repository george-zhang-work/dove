package com.dove.common.content;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;
import android.net.Uri;

import java.util.ArrayList;

/**
 * General purpose {@link android.content.ContentProvider} base class that use SQLiteDatabase for storage.
 */
public abstract class SQLiteContentProvider extends ContentProvider implements
    SQLiteTransactionListener {

    private static final int SLEEP_AFTER_YIELD_DELAY = 4000;

    /**
     * Maximum number of operations allowed in a batch between yield point.
     */
    private static final int MAX_OPERATIONS_PER_YIELD_POINT = 500;
    private final ThreadLocal<Boolean> mApplyingBatch = new ThreadLocal<Boolean>();
    protected SQLiteDatabase mDb;
    private SQLiteOpenHelper mOpenHelper;
    private volatile boolean mNotifyChange;

    @Override
    public boolean onCreate() {
        final Context context = getContext();
        mOpenHelper = getDatabaseHelper(context);
        return true;
    }

    protected abstract SQLiteOpenHelper getDatabaseHelper(Context context);

    /**
     * The equivalent of the {@link #insert} method, but invoked within a transaction.
     */
    protected abstract Uri insertInTransaction(Uri uri, ContentValues values);

    /**
     * The equivalent of the {@link #update} method, but invoked within a transaction.
     */
    protected abstract int updateInTransaction(Uri uri, ContentValues values, String selection,
                                               String[] selectionArgs);

    /**
     * The equivalent of the {@link #delete} method, but invoked within a transaction.
     */
    protected abstract int deleteInTransaction(Uri uri, String selection, String[] selectionArgs);

    /**
     * Notify the content of the provider has been changed.
     */
    protected abstract void notifyChange();

    /**
     * @return A {@link android.database.sqlite.SQLiteOpenHelper} is associated with this database.
     */
    public SQLiteOpenHelper getDatabaseHelper() {
        return mOpenHelper;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final Uri result;
        final boolean applyingBatch = applyingBatch();
        if (!applyingBatch) {
            mDb = mOpenHelper.getWritableDatabase();
            mDb.beginTransactionWithListener(this);
            try {
                result = insertInTransaction(uri, values);
                if (result != null) {
                    mNotifyChange = true;
                }
                mDb.setTransactionSuccessful();
            } finally {
                mDb.endTransaction();
            }

            onTransactionEnd();
        } else {
            result = insertInTransaction(uri, values);
            if (result != null) {
                mNotifyChange = true;
            }
        }
        return result;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final int numValues = values.length;
        mDb = mOpenHelper.getWritableDatabase();
        mDb.beginTransactionWithListener(this);
        try {
            for (int i = 0; i < numValues; i++) {
                final Uri result = insertInTransaction(uri, values[i]);
                if (result != null) {
                    mNotifyChange = true;
                }
                final boolean savedNotifyChange = mNotifyChange;
                final SQLiteDatabase savedDb = mDb;
                mDb.yieldIfContendedSafely();
                mDb = savedDb;
                mNotifyChange = savedNotifyChange;
            }
            mDb.setTransactionSuccessful();
        } finally {
            mDb.endTransaction();
        }

        onTransactionEnd();
        return numValues;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final int count;
        final boolean applyingBatch = applyingBatch();
        if (!applyingBatch) {
            mDb = mOpenHelper.getWritableDatabase();
            mDb.beginTransactionWithListener(this);
            try {
                count = updateInTransaction(uri, values, selection, selectionArgs);
                if (count > 0) {
                    mNotifyChange = true;
                }
                mDb.setTransactionSuccessful();
            } finally {
                mDb.endTransaction();
            }

            onTransactionEnd();
        } else {
            count = updateInTransaction(uri, values, selection, selectionArgs);
            if (count > 0) {
                mNotifyChange = true;
            }
        }
        return count;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final int count;
        final boolean applyingBatch = applyingBatch();
        if (!applyingBatch) {
            mDb = mOpenHelper.getWritableDatabase();
            mDb.beginTransactionWithListener(this);
            try {
                count = deleteInTransaction(uri, selection, selectionArgs);
                if (count > 0) {
                    mNotifyChange = true;
                }
                mDb.setTransactionSuccessful();
            } finally {
                mDb.endTransaction();
            }

            onTransactionEnd();
        } else {
            count = deleteInTransaction(uri, selection, selectionArgs);
            if (count > 0) {
                mNotifyChange = true;
            }
        }
        return 0;
    }

    private boolean applyingBatch() {
        return mApplyingBatch.get() != null && mApplyingBatch.get();
    }

    @Override
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations)
        throws OperationApplicationException {
        mDb = mOpenHelper.getWritableDatabase();
        mDb.beginTransactionWithListener(this);
        mApplyingBatch.set(true);
        try {
            final int numOperations = operations.size();
            final ContentProviderResult[] results = new ContentProviderResult[numOperations];
            int ypCount = 0;
            int opCount = 0;
            for (int i = 0; i < numOperations; i++) {
                if (++opCount > MAX_OPERATIONS_PER_YIELD_POINT) {
                    throw new OperationApplicationException(
                        "Too many content provider operations between yield points. "
                            + "The maximum number of operations per yield point is "
                            + MAX_OPERATIONS_PER_YIELD_POINT, ypCount
                    );
                }
                final ContentProviderOperation operation = operations.get(i);
                if (i > 0 && operation.isYieldAllowed()) {
                    opCount = 0;
                    final boolean savedNotifyChange = mNotifyChange;
                    if (mDb.yieldIfContendedSafely(SLEEP_AFTER_YIELD_DELAY)) {
                        mDb = mOpenHelper.getWritableDatabase();
                        mNotifyChange = savedNotifyChange;
                        ypCount++;
                    }
                }
                results[i] = operation.apply(this, results, i);
            }
            mDb.setTransactionSuccessful();
            return results;
        } finally {
            mApplyingBatch.set(false);
            mDb.endTransaction();
            onTransactionEnd();
        }
    }

    @Override
    public void onBegin() {
        onTransactionBegin();
    }

    @Override
    public void onCommit() {
        onTransactionCommit();
    }

    @Override
    public void onRollback() {
        // not used.
    }

    /**
     * Called immediately after the transaction begins.
     */
    protected void onTransactionBegin() {
    }

    /**
     * Called immediately after the transaction commit.
     */
    protected void onTransactionCommit() {
    }

    /**
     * Called immediately after a transaction ends.
     */
    protected void onTransactionEnd() {
        if (mNotifyChange) {
            mNotifyChange = false;
            notifyChange();
        }
    }
}
