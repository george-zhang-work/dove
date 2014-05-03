package com.dove.reader.provider.book;

import android.os.Parcel;
import android.os.Parcelable;

import com.dove.common.log.LogTag;
import com.dove.common.log.LogUtils;
import com.google.common.base.Objects;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by george on 4/26/14.
 */
public class Resource implements Parcelable {
    private static final String LOG_TAG = LogTag.getLogTag();

    private final String mId;
    private final String mHref;
    private final MediaType mMediaType;
    private final String mFallback;
    private final String mProperties;
    private final String mMediaOverlay;

    public Resource(String id, String href, MediaType mediaType,
                    String fallback, String properties, String mediaOverlay) {
        mId = id;
        mHref = href;
        mMediaType = mediaType;
        mFallback = fallback;
        mProperties = properties;
        mMediaOverlay = mediaOverlay;
    }

    public Resource(Parcel in, ClassLoader loader) {
        mId = in.readString();
        mHref = in.readString();
        mMediaType = in.readParcelable(loader);
        mFallback = in.readString();
        mProperties = in.readString();
        mMediaOverlay = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mHref);
        dest.writeParcelable(mMediaType, flags);
    }

    public String getId() {
        return mId;
    }

    public String getHref() {
        return mHref;
    }

    public MediaType getMediaType() {
        return mMediaType;
    }

    public String getFallback() {
        return mFallback;
    }

    public String getProperties() {
        return mProperties;
    }

    public String getMediaOverlay() {
        return mMediaOverlay;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mId, mHref, mMediaType, mFallback, mProperties, mMediaOverlay);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resource)) return false;

        final Resource resource = (Resource) o;
        return Objects.equal(mId, resource.mId)
            && Objects.equal(mHref, resource.mHref)
            && Objects.equal(mMediaType, resource.mMediaType)
            && Objects.equal(mFallback, resource.mFallback)
            && Objects.equal(mProperties, resource.mProperties)
            && Objects.equal(mMediaOverlay, resource.mMediaOverlay);
    }

    @Override
    public String toString() {
        try {
            return new JSONObject()
                .put(OPFContract.OPF.ID, mId)
                .put(OPFContract.OPF.HREF, mHref)
                .put(OPFContract.OPF.MEDIA_TYPE, mMediaType)
                .put(OPFContract.OPF.FALLBACK, mFallback)
                .put(OPFContract.OPF.PROPERTIES, mProperties)
                .put(OPFContract.OPF.MEDIA_OVERLAY, mMediaOverlay)
                .toString();
        } catch (JSONException e) {
            LogUtils.wtf(LOG_TAG, "Could not serialize the Resource with href %s", mHref);
        }
        return "";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final ClassLoaderCreator<Resource> CREATOR = new ClassLoaderCreator<Resource>() {
        @Override
        public Resource createFromParcel(Parcel source, ClassLoader loader) {
            return new Resource(source, loader);
        }

        @Override
        public Resource createFromParcel(Parcel source) {
            return new Resource(source, Resource.class.getClassLoader());
        }

        @Override
        public Resource[] newArray(int size) {
            return new Resource[size];
        }
    };
}
