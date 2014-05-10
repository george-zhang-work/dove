package com.dove.lib.oeb.ncx;

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
public class Audio extends SimpleElement {
    @SerializedName(OEBContract.Attributes.SRC)
    private String mSrc;
    @SerializedName(OEBContract.Attributes.CLIP_BEGIN)
    private String mClipBegin;
    @SerializedName(OEBContract.Attributes.CLIP_END)
    private String mClipEnd;

    public Audio() {
        super();
    }

    public Audio(Parcel in, ClassLoader loader) {
        super(in, loader);
        mSrc = in.readString();
        mClipBegin = in.readString();
        mClipEnd = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mSrc);
        dest.writeString(mClipBegin);
        dest.writeString(mClipEnd);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.AUDIO;
    }

    @Override
    protected void onParseAtrributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAtrributes(parser);
        mSrc = parser.getAttributeValue("", OEBContract.Attributes.SRC);
        mClipBegin = parser.getAttributeValue("", OEBContract.Attributes.CLIP_BEGIN);
        mClipEnd = parser.getAttributeValue("", OEBContract.Attributes.CLIP_END);
    }

    public static final ClassLoaderCreator<Audio> CREATOR = new ClassLoaderCreator<Audio>() {
        @Override
        public Audio createFromParcel(Parcel source, ClassLoader loader) {
            return new Audio(source, loader);
        }

        @Override
        public Audio createFromParcel(Parcel source) {
            return new Audio(source, null);
        }

        @Override
        public Audio[] newArray(int size) {
            return new Audio[size];
        }
    };
}
