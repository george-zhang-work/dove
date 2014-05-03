package com.dove.reader.provider.book;

import android.os.Parcel;

import com.dove.common.log.LogUtils;
import com.google.common.base.Objects;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by george on 5/2/14.
 */
public class Link extends Element {

    private final String mHref;
    private final String mRel;
    private final String mRefines;
    private final MediaType mMediaType;

    public Link(String id, String href, String rel, String refines, MediaType mediaType) {
        super(id);
        mHref = href;
        mRel = rel;
        mRefines = refines;
        mMediaType = mediaType;
    }

    public Link(Parcel in, ClassLoader loader) {
        super(in, loader);
        mHref = in.readString();
        mRel = in.readString();
        mRefines = in.readString();
        mMediaType = in.readParcelable(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mHref);
        dest.writeString(mRel);
        dest.writeString(mRefines);
        dest.writeParcelable(mMediaType, flags);
    }

    public String getHref() {
        return mHref;
    }

    public String getRel() {
        return mRel;
    }

    public String getRefines() {
        return mRefines;
    }

    public MediaType getMediaType() {
        return mMediaType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;
        if (!super.equals(o)) return false;

        final Link link = (Link) o;
        return Objects.equal(mHref, link.mHref)
            && Objects.equal(mRel, link.mRel)
            && Objects.equal(mRefines, link.mRefines)
            && Objects.equal(mMediaType, link.mMediaType);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), mHref, mRel, mRefines, mMediaType);
    }

    @Override
    public String toString() {
        try {
            return new JSONObject(super.toString())
                .put(OPFContract.OPF.HREF, mHref)
                .put(OPFContract.OPF.REL, mRel)
                .put(OPFContract.OPF.REFINES, mRefines)
                .put(OPFContract.OPF.MEDIA_TYPE, mMediaType)
                .toString();
        } catch (JSONException e) {
            LogUtils.wtf(LOG_TAG, "Could not serialize the Link with href %s ", mHref);
        }
        return "";
    }

    public static final ClassLoaderCreator<Link> CREATOR = new ClassLoaderCreator<Link>() {
        @Override
        public Link createFromParcel(Parcel source, ClassLoader loader) {
            return new Link(source, loader);
        }

        @Override
        public Link createFromParcel(Parcel source) {
            return new Link(source, Link.class.getClassLoader());
        }

        @Override
        public Link[] newArray(int size) {
            return new Link[size];
        }
    };
}
