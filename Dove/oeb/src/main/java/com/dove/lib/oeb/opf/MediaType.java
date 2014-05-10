package com.dove.lib.oeb.opf;

import android.os.Parcel;
import android.os.Parcelable;

import com.dove.lib.oeb.Element;
import com.dove.lib.oeb.OEBContract;
import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

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
    protected String getElementName() {
        return OEBContract.Elements.MEDIA_TYPE;
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

    @Override
    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
        mMediaType = parser.getAttributeValue("", OEBContract.Attributes.MEDIA_TYPE);
        mHandler = parser.getAttributeValue("", OEBContract.Attributes.HANDLER);
    }

    public static final Parcelable.Creator<MediaType> CREATOR = new Parcelable.ClassLoaderCreator<MediaType>() {
        @Override
        public MediaType createFromParcel(Parcel source, ClassLoader loader) {
            return new MediaType(source, loader);
        }

        @Override
        public MediaType createFromParcel(Parcel source) {
            return new MediaType(source, null);
        }

        @Override
        public MediaType[] newArray(int size) {
            return new MediaType[size];
        }
    };
}
