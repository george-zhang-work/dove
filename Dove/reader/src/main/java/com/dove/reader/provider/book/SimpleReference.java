package com.dove.reader.provider.book;

import android.os.Parcel;

import com.dove.common.log.LogUtils;
import com.google.common.base.Objects;

import org.json.JSONException;
import org.json.JSONObject;

public class SimpleReference extends Reference {

    protected final String mId;

    public SimpleReference(String id, Resource resource) {
        super(resource);
        mId = id;
    }

    public SimpleReference(Parcel in, ClassLoader loader) {
        super(in, loader);
        mId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleReference)) return false;
        if (!super.equals(o)) return false;

        final SimpleReference that = (SimpleReference) o;
        return Objects.equal(mId, that.mId);
    }

    @Override
    public String toString() {
        try {
            return new JSONObject(super.toString())
                .put(OPFContract.OPF.ID, mId)
                .toString();
        } catch (JSONException e) {
            LogUtils.wtf(LOG_TAG, "Could not serialize the SimpleReference " +
                "with resource id=%s, resource=%s", mId, mResource);
        }
        return "";
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), mId);
    }

    public static final ClassLoaderCreator<SimpleTextElement> CREATOR = new ClassLoaderCreator<SimpleTextElement>() {
        @Override
        public SimpleTextElement createFromParcel(Parcel source, ClassLoader loader) {
            return new SimpleTextElement(source, loader);
        }

        @Override
        public SimpleTextElement createFromParcel(Parcel source) {
            return new SimpleTextElement(source, SimpleReference.class.getClassLoader());
        }

        @Override
        public SimpleTextElement[] newArray(int size) {
            return new SimpleTextElement[size];
        }
    };
}
