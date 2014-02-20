
package com.dove.reader.providers;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A fold is a collection of book categories, and perhaps other folders.
 */
public class Folder implements Parcelable, Comparable<Folder> {

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

}
