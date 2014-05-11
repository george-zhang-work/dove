package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.Element;
import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.ParcelableCreator;
import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

/**
 * Created by george on 5/8/14.
 */
public class MediaType extends Element {
    @SerializedName(OEBContract.Attributes.MEDIA_TYPE)
    String mMediaType;
    @SerializedName(OEBContract.Attributes.HANDLER)
    String mHandler;

    public MediaType() {
        super();
    }

    public MediaType(Parcel in, ClassLoader loader) {
        super(in, loader);
        mMediaType = in.readString();
        mHandler = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mMediaType);
        dest.writeString(mHandler);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MediaType)) return false;

        final MediaType mediaType = (MediaType) o;
        return Objects.equal(mHandler, mediaType.mHandler);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mHandler);
    }

    public static final ClassLoaderCreator<MediaType> CREATOR = new ParcelableCreator<>(MediaType.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.MEDIA_TYPE;
    }

    @Override
    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
        mMediaType = parser.getAttributeValue("", OEBContract.Attributes.MEDIA_TYPE);
        mHandler = parser.getAttributeValue("", OEBContract.Attributes.HANDLER);
    }

    @Override
    protected void onSerializeAttributes(XmlSerializer serializer) throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeAttributes(serializer);
        serializeValue(serializer, "", OEBContract.Attributes.MEDIA_TYPE, mMediaType);
        serializeValue(serializer, "", OEBContract.Attributes.HANDLER, mHandler);
    }
}
