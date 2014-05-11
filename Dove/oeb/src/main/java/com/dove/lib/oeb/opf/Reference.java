package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.ParcelableCreator;
import com.dove.lib.oeb.SimpleElement;
import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class Reference extends SimpleElement {

    @SerializedName(OEBContract.Attributes.TYPE)
    private String mType;

    @SerializedName(OEBContract.Attributes.TITLE)
    private String mTitle;

    @SerializedName(OEBContract.Attributes.HREF)
    private String mHref;

    public Reference() {
        super();
    }

    public Reference(Parcel in, ClassLoader loader) {
        super(in, loader);
        mType = in.readString();
        mTitle = in.readString();
        mHref = in.readString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reference)) return false;

        final Reference reference = (Reference) o;
        return Objects.equal(mHref, reference.mHref)
            && Objects.equal(mType, reference.mType);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mHref, mType);
    }

    public static final ClassLoaderCreator<Reference> CREATOR = new ParcelableCreator<>(Reference.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.REFERENCE;
    }

    @Override
    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
        mType = parser.getAttributeValue("", OEBContract.Attributes.TYPE);
        mTitle = parser.getAttributeValue("", OEBContract.Attributes.TITLE);
        mHref = parser.getAttributeValue("", OEBContract.Attributes.HREF);
    }

    @Override
    protected void onSerializeAttributes(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeAttributes(serializer);
        serializeValue(serializer, "", OEBContract.Attributes.TYPE, mType);
        serializeValue(serializer, "", OEBContract.Attributes.TITLE, mTitle);
        serializeValue(serializer, "", OEBContract.Attributes.HREF, mHref);
    }
}
