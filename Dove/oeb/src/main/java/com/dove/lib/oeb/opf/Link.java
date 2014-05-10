package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.SimpleElement;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by george on 5/8/14.
 */
public class Link extends SimpleElement {
    @SerializedName(OEBContract.Attributes.HREF)
    private String mHref;
    @SerializedName(OEBContract.Attributes.REL)
    private String mRel;
    @SerializedName(OEBContract.Attributes.REFINES)
    private String mRefines;
    @SerializedName(OEBContract.Attributes.MEDIA_TYPE)
    private String mMediaType;

    public Link() {
        super();
    }

    public Link(Parcel in, ClassLoader loader) {
        super(in, loader);
        mHref = in.readString();
        mRel = in.readString();
        mRefines = in.readString();
        mMediaType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mHref);
        dest.writeString(mRel);
        dest.writeString(mRefines);
        dest.writeString(mMediaType);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.LINK;
    }

    @Override
    protected void onParseAtrributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAtrributes(parser);
        mHref = parser.getAttributeValue("", OEBContract.Attributes.HREF);
        mRel = parser.getAttributeValue("", OEBContract.Attributes.REL);
        mRefines = parser.getAttributeValue("", OEBContract.Attributes.REFINES);
        mMediaType = parser.getAttributeValue("", OEBContract.Attributes.MEDIA_TYPE);
    }

    public static final ClassLoaderCreator<Link> CREATOR = new ClassLoaderCreator<Link>() {
        @Override
        public Link createFromParcel(Parcel source, ClassLoader loader) {
            return new Link(source, loader);
        }

        @Override
        public Link createFromParcel(Parcel source) {
            return new Link(source, null);
        }

        @Override
        public Link[] newArray(int size) {
            return new Link[size];
        }
    };
}
