package com.dove.reader.provider.book;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by george on 4/26/14.
 */
public class TableOfContents implements Parcelable {
    private final TOCReference mRootReference;

    public TableOfContents(TOCReference rootReference) {
        mRootReference = rootReference;
    }

    public TableOfContents(Parcel in, ClassLoader loader) {
        mRootReference = in.readParcelable(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mRootReference, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final ClassLoaderCreator<TableOfContents> CREATOR = new ClassLoaderCreator<TableOfContents>() {
        @Override
        public TableOfContents createFromParcel(Parcel source, ClassLoader loader) {
            return new TableOfContents(source, loader);
        }

        @Override
        public TableOfContents createFromParcel(Parcel source) {
            return new TableOfContents(source, TableOfContents.class.getClassLoader());
        }

        @Override
        public TableOfContents[] newArray(int size) {
            return new TableOfContents[size];
        }
    };
}
