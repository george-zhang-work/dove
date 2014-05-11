package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.ParcelableCreator;
import com.dove.lib.oeb.SimpleElement;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

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

    public static final ClassLoaderCreator<Link> CREATOR = new ParcelableCreator<>(Link.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.LINK;
    }

    @Override
    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
        mHref = parser.getAttributeValue("", OEBContract.Attributes.HREF);
        mRel = parser.getAttributeValue("", OEBContract.Attributes.REL);
        mRefines = parser.getAttributeValue("", OEBContract.Attributes.REFINES);
        mMediaType = parser.getAttributeValue("", OEBContract.Attributes.MEDIA_TYPE);
    }

    @Override
    protected void onSerializeAttributes(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeAttributes(serializer);
        serializeValue(serializer, "", OEBContract.Attributes.HANDLER, mHref);
        serializeValue(serializer, "", OEBContract.Attributes.REL, mRel);
        serializeValue(serializer, "", OEBContract.Attributes.REFINES, mRefines);
        serializeValue(serializer, "", OEBContract.Attributes.MEDIA_TYPE, mMediaType);

    }
}
