package com.dove.reader.provider.book;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.dove.common.content.CursorCreator;
import com.dove.reader.provider.ReaderContract;

public class Content implements Parcelable {
    private final int mIcon;
    private final String mNumber;
    private final String mText;
    private final String mIndexed;

    public static final CursorCreator<Content> FACTORY = new CursorCreator<Content>() {
        @Override
        public Content createFromCursor(Cursor cursor) {
            return new Content(cursor);
        }

        @Override
        public String toString() {
            return "Content CursorCreator";
        }
    };

    public static final ClassLoaderCreator<Content> CREATOR = new ClassLoaderCreator<Content>() {
        @Override
        public Content createFromParcel(Parcel source, ClassLoader loader) {
            return new Content(source, loader);
        }

        @Override
        public Content createFromParcel(Parcel source) {
            return new Content(source, null);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };

    public Content(Cursor cursor) {
        mIcon = cursor.getInt(cursor.getColumnIndex(ReaderContract.Contents.ICON));
        mNumber = cursor.getString(cursor.getColumnIndex(ReaderContract.Contents.NUMBER));
        mText = cursor.getString(cursor.getColumnIndex(ReaderContract.Contents.TEXT));
        mIndexed = cursor.getString(cursor.getColumnIndex(ReaderContract.Contents.INDEXED));
    }

    public Content(Parcel source, ClassLoader loader) {
        mIcon = source.readInt();
        mNumber = source.readString();
        mText = source.readString();
        mIndexed = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mIcon);
        dest.writeString(mNumber);
        dest.writeString(mText);
        dest.writeString(mIndexed);
    }

    @Override
    public String toString() {
        return "Content{" +
            "mIcon=" + mIcon +
            ", mNumber='" + mNumber + '\'' +
            ", mText='" + mText + '\'' +
            ", mIndexed='" + mIndexed + '\'' +
            '}';
    }
}
