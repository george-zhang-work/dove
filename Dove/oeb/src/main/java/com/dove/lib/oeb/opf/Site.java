package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.SimpleElement;
import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

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
    protected void onParseAtrributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAtrributes(parser);
        mTitle = parser.getAttributeValue("", OEBContract.Attributes.TITLE);
        mHref = parser.getAttributeValue("", OEBContract.Attributes.HREF);
    }

    public static final ClassLoaderCreator<Site> CREATOR = new ClassLoaderCreator<Site>() {
        @Override
        public Site createFromParcel(Parcel source, ClassLoader loader) {
            return new Site(source, loader);
        }

        @Override
        public Site createFromParcel(Parcel source) {
            return new Site(source, null);
        }

        @Override
        public Site[] newArray(int size) {
            return new Site[size];
        }
    };
}
