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

public class Site extends SimpleElement {

    @SerializedName(OEBContract.Attributes.TITLE)
    private String mTitle;

    @SerializedName(OEBContract.Attributes.HREF)
    private String mHref;

    public Site() {
        super();
    }

    public Site(Parcel in, ClassLoader loader) {
        super(in, loader);
        mTitle = in.readString();
        mHref = in.readString();
    }

    public static final ClassLoaderCreator<Site> CREATOR = new ParcelableCreator<>(Site.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.SITE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Site)) return false;

        final Site reference = (Site) o;
        return Objects.equal(mHref, reference.mHref);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mHref);
    }

    @Override
    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
        mTitle = parser.getAttributeValue("", OEBContract.Attributes.TITLE);
        mHref = parser.getAttributeValue("", OEBContract.Attributes.HREF);
    }

    @Override
    protected void onSerializeAttributes(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeAttributes(serializer);
        serializeValue(serializer, "", OEBContract.Attributes.TITLE, mTitle);
        serializeValue(serializer, "", OEBContract.Attributes.HREF, mHref);
    }
}
