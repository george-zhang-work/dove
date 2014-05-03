package com.dove.reader.provider.book;

import android.os.Parcel;

import com.dove.common.log.LogUtils;
import com.google.common.base.Objects;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by george on 4/26/14.
 */
public class GuideReference extends Reference {

    private final String mTitle;
    private final Type mType;

    public GuideReference(Resource resource, String title, String type) {
        super(resource);
        mTitle = title;
        mType = Type.fromValue(type);
    }

    public GuideReference(Parcel in, ClassLoader loader) {
        super(in, loader);
        mTitle = in.readString();
        mType = Type.fromValue(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mTitle);
        dest.writeString(mType.toString());
    }

    public String getTitle() {
        return mTitle;
    }

    public String getType() {
        return mType != null ? mType.toString() : null;
    }

    @Override
    public String toString() {
        try {
            return new JSONObject(super.toString())
                .put(OPFContract.OPF.TITLE, mTitle)
                .put(OPFContract.OPF.TYPE, mType.toString())
                .toString();
        } catch (JSONException e) {
            LogUtils.wtf(LOG_TAG, "Could not serialize the GuideReference with reference " + getResourceId());
        }
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GuideReference)) return false;
        if (!super.equals(o)) return false;

        final GuideReference that = (GuideReference) o;
        return Objects.equal(mTitle, that.mTitle) && Objects.equal(mType, that.mType);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), mTitle, mType);
    }

    public static final ClassLoaderCreator<GuideReference> CREATOR = new ClassLoaderCreator<GuideReference>() {
        @Override
        public GuideReference createFromParcel(Parcel source, ClassLoader loader) {
            return new GuideReference(source, loader);
        }

        @Override
        public GuideReference createFromParcel(Parcel source) {
            return new GuideReference(source, GuideReference.class.getClassLoader());
        }

        @Override
        public GuideReference[] newArray(int size) {
            return new GuideReference[size];
        }
    };
}
