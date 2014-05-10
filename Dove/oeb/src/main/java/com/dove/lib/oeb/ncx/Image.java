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
public class Image extends SimpleElement {
    @SerializedName(OEBContract.Attributes.SRC)
    private String mSrc;

    public Image() {
        super();
    }

    public Image(Parcel in, ClassLoader loader) {
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
        return OEBContract.Elements.IMAGE;
    }

    @Override
    protected void onParseAtrributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAtrributes(parser);
        mSrc = parser.getAttributeValue("", OEBContract.Attributes.SRC);
    }

    public static final ClassLoaderCreator<Image> CREATOR = new ClassLoaderCreator<Image>() {
        @Override
        public Image createFromParcel(Parcel source, ClassLoader loader) {
            return new Image(source, loader);
        }

        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source, null);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
