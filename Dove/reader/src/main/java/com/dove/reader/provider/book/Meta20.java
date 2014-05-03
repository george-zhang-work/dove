package com.dove.reader.provider.book;

import android.os.Parcel;
import android.os.Parcelable;

import com.dove.common.log.LogTag;
import com.dove.common.log.LogUtils;
import com.google.common.base.Objects;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by george on 5/1/14.
 */
public class Meta20 implements Parcelable {
    protected static final String LOG_TAG = LogTag.getLogTag();

    private final String mContent;
    private final String mName;

    public Meta20(String content, String name) {
        mContent = content;
        mName = name;
    }

    public Meta20(Parcel in, ClassLoader classLoader) {
        mName = in.readString();
        mContent = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mContent);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getContent() {
        return mContent;
    }

    public String getName() {
        return mName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meta20)) return false;

        final Meta20 meta20 = (Meta20) o;
        return Objects.equal(mContent, meta20.mContent)
            && Objects.equal(mName, meta20.mContent);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mName, mContent);
    }

    @Override
    public String toString() {
        try {
            return new JSONObject()
                .put(OPFContract.OPF.CONTENT, mContent)
                .put(OPFContract.OPF.NAME, mName)
                .toString();
        } catch (JSONException e) {
            LogUtils.wtf(LOG_TAG, "Could not serialize the Meta20 with name %s" + mName);
        }
        return "";
    }

    public static final ClassLoaderCreator<Meta20> CREATOR = new ClassLoaderCreator<Meta20>() {
        @Override
        public Meta20 createFromParcel(Parcel source, ClassLoader loader) {
            return new Meta20(source, loader);
        }

        @Override
        public Meta20 createFromParcel(Parcel source) {
            return new Meta20(source, Meta20.class.getClassLoader());
        }

        @Override
        public Meta20[] newArray(int size) {
            return new Meta20[size];
        }
    };
}
