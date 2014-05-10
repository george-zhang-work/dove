package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.SimpleElement;
import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by george on 5/8/14.
 */
public class Meta20 extends SimpleElement {
    @SerializedName(OEBContract.Attributes.XML_LANG)
    private String mXmlLang;
    @SerializedName(OEBContract.Attributes.NAME)
    private String mName;
    @SerializedName(OEBContract.Attributes.CONTENT)
    private String mContent;
    @SerializedName(OEBContract.Attributes.SCHEME)
    private String mScheme;

    public Meta20() {
        super();
    }

    public Meta20(Parcel in, ClassLoader loader) {
        super(in, loader);
        mXmlLang = in.readString();
        mName = in.readString();
        mContent = in.readString();
        mScheme = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mXmlLang);
        dest.writeString(mName);
        dest.writeString(mContent);
        dest.writeString(mScheme);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.META;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meta20)) return false;

        final Meta20 meta20 = (Meta20) o;
        return Objects.equal(mContent, meta20.mContent)
            && Objects.equal(mName, meta20.mName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mName, mContent);
    }

    @Override
    protected void onParseAtrributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAtrributes(parser);
        mXmlLang = parser.getAttributeValue(OEBContract.Namespaces.XML, OEBContract.Attributes.XML_LANG);
        mName = parser.getAttributeValue("", OEBContract.Attributes.NAME);
        mContent = parser.getAttributeValue("", OEBContract.Attributes.CONTENT);
        mScheme = parser.getAttributeValue("", OEBContract.Attributes.SCHEME);
    }

    public static final ClassLoaderCreator<Meta20> CREATOR = new ClassLoaderCreator<Meta20>() {
        @Override
        public Meta20 createFromParcel(Parcel source, ClassLoader loader) {
            return new Meta20(source, loader);
        }

        @Override
        public Meta20 createFromParcel(Parcel source) {
            return new Meta20(source, null);
        }

        @Override
        public Meta20[] newArray(int size) {
            return new Meta20[size];
        }
    };
}
