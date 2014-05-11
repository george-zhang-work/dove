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
 * Created by george on 5/6/14.
 */
public class Item extends SimpleElement {

    @SerializedName(OEBContract.Attributes.HREF)
    protected String mHref;

    @SerializedName(OEBContract.Attributes.MEDIA_TYPE)
    protected String mMediaType;

    @SerializedName(OEBContract.Attributes.FALLBACK)
    protected String mFallback;

    @SerializedName(OEBContract.Attributes.PROPERTIES)
    protected String mProperties;

    @SerializedName(OEBContract.Attributes.MEDIA_OVERLAY)
    protected String mMediaOverlay;

    public Item() {
        super();
    }

    public Item(Parcel in, ClassLoader loader) {
        super(in, loader);
        mHref = in.readString();
        mMediaType = in.readString();
        mFallback = in.readString();
        mProperties = in.readString();
        mMediaOverlay = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mHref);
        dest.writeString(mMediaType);
        dest.writeString(mFallback);
        dest.writeString(mProperties);
        dest.writeString(mMediaOverlay);
    }

    public String getHref() {
        return mHref;
    }

    public String getMediaType() {
        return mMediaType;
    }

    public String getFallback() {
        return mFallback;
    }

    public String getProperties() {
        return mProperties;
    }

    public String getMediaOverlay() {
        return mMediaOverlay;
    }

    public static final ClassLoaderCreator<Item> CREATOR = new ParcelableCreator<>(Item.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.ITEM;
    }

    @Override
    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
        mHref = parser.getAttributeValue("", OEBContract.Attributes.HREF);
        mMediaType = parser.getAttributeValue("", OEBContract.Attributes.MEDIA_TYPE);
        mFallback = parser.getAttributeValue("", OEBContract.Attributes.FALLBACK);
        mProperties = parser.getAttributeValue("", OEBContract.Attributes.PROPERTIES);
        mMediaOverlay = parser.getAttributeValue("", OEBContract.Attributes.MEDIA_OVERLAY);
    }

    @Override
    protected void onSerializeAttributes(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeAttributes(serializer);
        serializeValue(serializer, "", OEBContract.Attributes.HREF, mHref);
        serializeValue(serializer, "", OEBContract.Attributes.MEDIA_TYPE, mMediaType);
        serializeValue(serializer, "", OEBContract.Attributes.FALLBACK, mFallback);
        serializeValue(serializer, "", OEBContract.Attributes.PROPERTIES, mProperties);
        serializeValue(serializer, "", OEBContract.Attributes.MEDIA_OVERLAY, mMediaOverlay);
    }
}
