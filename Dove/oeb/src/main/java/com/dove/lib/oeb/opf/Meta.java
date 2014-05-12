package com.dove.lib.oeb.opf;

import android.os.Parcel;
import android.os.Parcelable;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.ParcelableCreator;
import com.dove.lib.oeb.SimpleTextElement;
import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

/**
 * Created by george on 5/1/14.
 */
public class Meta extends SimpleTextElement {
    @SerializedName(OEBContract.Attributes.REFINES)
    private String mRefines;
    @SerializedName(OEBContract.Attributes.PROPERTY)
    private String mProperty;
    @SerializedName(OEBContract.Attributes.SCHEME)
    private String mScheme;

    public Meta() {
    }

    public Meta(String refines, String property, String scheme) {
        mRefines = refines;
        mProperty = property;
        mScheme = scheme;
    }

    public Meta(Parcel in, ClassLoader loader) {
        super(in, loader);
        mRefines = in.readString();
        mProperty = in.readString();
        mScheme = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mRefines);
        dest.writeString(mProperty);
        dest.writeString(mScheme);
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

    public static final Parcelable.ClassLoaderCreator<Meta> CREATOR = new ParcelableCreator<>(Meta.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.META;
    }

    @Override
    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
        mProperty = parser.getAttributeValue("", OEBContract.Attributes.PROPERTY);
        mRefines = parser.getAttributeValue("", OEBContract.Attributes.REFINES);
        mScheme = parser.getAttributeValue("", OEBContract.Attributes.SCHEME);
    }

    @Override
    protected void onSerializeAttributes(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeAttributes(serializer);
        serializeValue(serializer, "", OEBContract.Attributes.REFINES, mRefines);
        serializeValue(serializer, "", OEBContract.Attributes.PROPERTY, mProperty);
        serializeValue(serializer, "", OEBContract.Attributes.SCHEME, mScheme);
    }
}

