package com.dove.reader.provider.book;

import android.os.Parcel;

import com.dove.common.log.LogUtils;
import com.google.common.base.Objects;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by george on 5/1/14.
 */
public class Meta extends TextElement {
    private final String mRefines;
    private final Property mProperty;
    private final Scheme mScheme;

    public Meta(String id, String refines, Property property, Scheme scheme, String text) {
        super(id, text);
        mRefines = refines;
        mProperty = property;
        mScheme = scheme;
    }

    public Meta(Parcel in, ClassLoader loader) {
        super(in, loader);
        mRefines = in.readString();
        mProperty = Property.fromValue(in.readString());
        mScheme = Scheme.formValue(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mRefines);
        dest.writeString(mProperty.toString());
        dest.writeString(mScheme.toString());
    }

    public String getRefines() {
        return mRefines;
    }

    public Property getProperty() {
        return mProperty;
    }

    public Scheme getScheme() {
        return mScheme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meta)) return false;
        if (!super.equals(o)) return false;

        final Meta meta = (Meta) o;
        return Objects.equal(mProperty, meta.mProperty)
            && Objects.equal(mRefines, meta.mRefines)
            && Objects.equal(mScheme, meta.mScheme);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), mRefines, mProperty, mScheme);
    }

    @Override
    public String toString() {
        try {
            return new JSONObject(super.toString())
                .put(OPFContract.OPF.PROPERTY, mProperty)
                .put(OPFContract.OPF.SCHEME, mScheme)
                .put(OPFContract.OPF.REFINES, mRefines)
                .toString();
        } catch (JSONException e) {
            LogUtils.wtf(LOG_TAG, "Could not serialize the Meta with text %s" + mText);
        }
        return "";
    }

    public static final ClassLoaderCreator<Meta> CREATOR = new ClassLoaderCreator<Meta>() {
        @Override
        public Meta createFromParcel(Parcel source, ClassLoader loader) {
            return new Meta(source, loader);
        }

        @Override
        public Meta createFromParcel(Parcel source) {
            return new Meta(source, Meta.class.getClassLoader());
        }

        @Override
        public Meta[] newArray(int size) {
            return new Meta[size];
        }
    };
}

