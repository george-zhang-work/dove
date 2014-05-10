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
public class Content extends SimpleElement {
    @SerializedName(OEBContract.Attributes.SRC)
    private String mSrc;

    public Content() {
        super();
    }

    public Content(Parcel in, ClassLoader loader) {
        super(in, loader);
        mSrc = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mSrc);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.CONTENT;
    }

    @Override
    protected void onParseAtrributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAtrributes(parser);
        mSrc = parser.getAttributeValue("", OEBContract.Attributes.SRC);
    }

    public static final ClassLoaderCreator<Content> CREATOR = new ClassLoaderCreator<Content>() {
        @Override
        public Content createFromParcel(Parcel source, ClassLoader loader) {
            return new Content(source, loader);
        }

        @Override
        public Content createFromParcel(Parcel source) {
            return new Content(source, null);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };
}
