package com.dove.lib.oeb.opf;

import android.os.Parcel;
import android.os.Parcelable;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.SimpleTextElement;
import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by george on 5/1/14.
 */
public class Meta extends SimpleTextElement {
    @SerializedName(OEBContract.Attributes.REFINES)
    private String mRefines;
    @SerializedName(OEBContract.Attributes.PROPERTY)
    private Property mProperty;
    @SerializedName(OEBContract.Attributes.SCHEME)
    private String mScheme;

    public Meta() {
    }

    public Meta(Parcel in, ClassLoader loader) {
        super(in, loader);
        mRefines = in.readString();
        mProperty = Property.fromValue(in.readString());
        mScheme = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mRefines);
        dest.writeString(mProperty.toString());
        dest.writeString(mScheme.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meta)) return false;

        final Meta meta = (Meta) o;
        return Objects.equal(mProperty, meta.mProperty)
            && Objects.equal(mRefines, meta.mRefines);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mRefines, mProperty);
    }

    public String getRefines() {
        return mRefines;
    }

    public Property getProperty() {
        return mProperty;
    }

    public String getScheme() {
        return mScheme;
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.META;
    }

    @Override
    protected void onParseAtrributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAtrributes(parser);
        mProperty = Property.fromValue(parser.getAttributeValue("", OEBContract.Attributes.PROPERTY));
        mRefines = parser.getAttributeValue("", OEBContract.Attributes.REFINES);
        mScheme = parser.getAttributeValue("", OEBContract.Attributes.SCHEME);
    }

    public static final Parcelable.ClassLoaderCreator<Meta> CREATOR = new Parcelable.ClassLoaderCreator<Meta>() {
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

