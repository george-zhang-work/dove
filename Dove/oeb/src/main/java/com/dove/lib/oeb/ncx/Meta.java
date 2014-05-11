package com.dove.lib.oeb.ncx;

import android.os.Parcel;

import com.dove.lib.oeb.Element;
import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.ParcelableCreator;
import com.dove.lib.oeb.opf.Meta20;
import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

/**
 * Created by george on 5/8/14.
 */
public class Meta extends Element {
    @SerializedName(OEBContract.Attributes.NAME)
    private String mName;
    @SerializedName(OEBContract.Attributes.CONTENT)
    private String mContent;
    @SerializedName(OEBContract.Attributes.SCHEME)
    private String mScheme;

    public Meta() {
        super();
    }

    public Meta(Parcel in, ClassLoader loader) {
        super(in, loader);
        mName = in.readString();
        mContent = in.readString();
        mScheme = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mName);
        dest.writeString(mContent);
        dest.writeString(mScheme);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meta20)) return false;

        final Meta meta = (Meta) o;
        return Objects.equal(mContent, meta.mContent)
            && Objects.equal(mName, meta.mName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mName, mContent);
    }

    public static final ClassLoaderCreator<Meta> CREATOR = new ParcelableCreator<>(Meta.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.META;
    }

    @Override
    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
        mName = parser.getAttributeValue("", OEBContract.Attributes.NAME);
        mContent = parser.getAttributeValue("", OEBContract.Attributes.CONTENT);
        mScheme = parser.getAttributeValue("", OEBContract.Attributes.SCHEME);
    }

    @Override
    protected void onSerializeAttributes(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeAttributes(serializer);
        serializeValue(serializer, "", OEBContract.Attributes.NAME, mName);
        serializeValue(serializer, "", OEBContract.Attributes.CONTENT, mContent);
        serializeValue(serializer, "", OEBContract.Attributes.SCHEME, mScheme);
    }
}
