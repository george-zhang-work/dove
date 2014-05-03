package com.dove.reader.provider.book;

import android.os.Parcel;

import com.dove.common.log.LogUtils;
import com.google.common.base.Objects;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by george on 5/1/14.
 */
public class Element implements ParcelableElement {

    protected String mId;

    public Element() {
        this(null);
    }

    public Element(String id) {
        mId = id;
    }

    public Element(Parcel in, ClassLoader loader) {
        mId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Element)) return false;

        final Element that = (Element) o;
        return Objects.equal(mId, that.mId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        try {
            return new JSONObject()
                .put(OPFContract.OPF.ID, mId)
                .toString();
        } catch (JSONException e) {
            LogUtils.wtf(LOG_TAG, "Could not serialize the Element with id %s ", mId);
        }
        return "";
    }

    public static final ClassLoaderCreator<Element> CREATOR = new ClassLoaderCreator<Element>() {
        @Override
        public Element createFromParcel(Parcel source, ClassLoader loader) {
            return new Element(source, loader);
        }

        @Override
        public Element createFromParcel(Parcel source) {
            return new Element(source, Element.class.getClassLoader());
        }

        @Override
        public Element[] newArray(int size) {
            return new Element[size];
        }
    };
}
