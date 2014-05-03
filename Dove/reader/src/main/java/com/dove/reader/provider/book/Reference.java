package com.dove.reader.provider.book;

import android.os.Parcel;

import com.dove.common.log.LogUtils;
import com.google.common.base.Objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Reference implements ParcelableElement {

    protected final Resource mResource;

    public Reference(Resource resource) {
        mResource = resource;
    }

    public Reference(Parcel in, ClassLoader loader) {
        mResource = in.readParcelable(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mResource, flags);
    }

    public Resource getResource() {
        return mResource;
    }

    public String getResourceId() {
        return mResource.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reference)) return false;

        final Reference reference = (Reference) o;
        return Objects.equal(mResource, reference.mResource);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), mResource);
    }

    @Override
    public String toString() {
        try {
            return new JSONObject(super.toString())
                .put("resource", mResource)
                .toString();
        } catch (JSONException e) {
            LogUtils.wtf(LOG_TAG, "Could not serialize the Reference with resource %s", mResource);
        }
        return "";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final ClassLoaderCreator<Reference> CREATOR = new ClassLoaderCreator<Reference>() {
        @Override
        public Reference createFromParcel(Parcel source, ClassLoader loader) {
            return new Reference(source, loader);
        }

        @Override
        public Reference createFromParcel(Parcel source) {
            return new Reference(source, Reference.class.getClassLoader());
        }

        @Override
        public Reference[] newArray(int size) {
            return new Reference[size];
        }
    };
}
