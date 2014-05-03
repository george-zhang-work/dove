package com.dove.reader.provider.book;

import android.os.Parcel;
import android.os.Parcelable;

import com.dove.common.log.LogUtils;
import com.google.common.base.Objects;

import org.json.JSONException;
import org.json.JSONObject;

public class IDReference extends Element implements Parcelable {
    protected final Resource mResource;

    public IDReference(Resource resource) {
        this(null, resource);
    }

    public IDReference(String id, Resource resource) {
        super(id);
        mResource = resource;
    }

    public IDReference(Parcel in, ClassLoader loader) {
        super(in, loader);
        mResource = in.readParcelable(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
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
        if (this == o) {
            return true;
        }
        if (!(o instanceof IDReference)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final IDReference reference = (IDReference) o;
        if (!Objects.equal(mResource, reference.mResource)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        try {
            return new JSONObject(super.toString())
                .put("resource", mResource)
                .put("id", null)
                .toString();
        } catch (JSONException e) {
            LogUtils.wtf(LOG_TAG, "Could not serialize the SpineReference with resource " + mResource);
        }
        return "";
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), mResource);
    }
}
