
package com.dove.reader.providers;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * A fold is a collection of book categories, and perhaps other folders.
 */
public class Folder implements Parcelable, Comparable<Folder> {

    /**
     * The content provider URI to return the list of conversations in this
     * folder.
     */
    private final Uri mChildFoldersUri;

    public Folder() {
        // TODO
        mChildFoldersUri = Uri.EMPTY;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub

    }

    @Override
    public int compareTo(Folder another) {
        // TODO Auto-generated method stub
        return 0;
    }

    public Uri getChildFoldersUri() {
        return mChildFoldersUri;
    }
}
