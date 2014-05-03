package com.dove.reader.provider.book;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.dove.common.log.LogTag;
import com.dove.common.log.LogUtils;
import com.google.common.base.Objects;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The author model of this book. It presents both Creator and Contributors model.
 */
public final class Author implements Parcelable {
    private static final String LOG_TAG = LogTag.getLogTag();

    private final String mDisplayName;
    private final Relator mRelator;

    public Author(String displayName, String role) {
        mDisplayName = displayName;
        mRelator = !TextUtils.isEmpty(role) ? Relator.byCode(role) : Relator.AUTHOR;
    }

    public Author(Cursor cursor) {
        mDisplayName = cursor.getString(cursor.getColumnIndex(OPFContract.OPF.DISPLAY_NAME));
        mRelator = Relator.byCode(cursor.getString(cursor.getColumnIndex(OPFContract.OPF.RELATOR)));
    }

    public Author(Parcel in) {
        mDisplayName = in.readString();
        mRelator = Relator.byCode(in.readString());
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public Relator getRelator() {
        return mRelator;
    }

    public String getRole() {
        return mRelator.getCode();
    }

    @Override
    public String toString() {
        final JSONObject json = new JSONObject();
        try {
            json.put(OPFContract.OPF.DISPLAY_NAME, mDisplayName);
            json.put(OPFContract.OPF.RELATOR, mRelator);
        } catch (JSONException e) {
            LogUtils.wtf(LOG_TAG, "Could not serialize the Author with name %s", mDisplayName);
        }
        return json.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Author)) {
            return false;
        }
        final Author author = (Author) o;
        if (!Objects.equal(mDisplayName, author.mDisplayName) || mRelator != author.mRelator) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mDisplayName, mRelator);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mDisplayName);
        dest.writeString(mRelator.getCode());
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {

        @Override
        public Author createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };

}
