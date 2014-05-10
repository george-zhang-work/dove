package com.dove.lib.oeb;

import android.os.Parcel;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by george on 5/5/14.
 */
public class ComplexTextElement extends SimpleTextElement {

    @SerializedName(OEBContract.Attributes.XML_LANG)
    protected String mXmlLang;
    @SerializedName(OEBContract.Attributes.DIR)
    protected Direction mDir;

    public ComplexTextElement() {
    }

    public ComplexTextElement(Parcel in, ClassLoader loader) {
        super(in, loader);
        mXmlLang = in.readString();
        mDir = Direction.fromValue(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mXmlLang);
        dest.writeString(mDir.toString());
    }

    public String getXmlLang() {
        return mXmlLang;
    }

    public void setXmlLang(String xmlLang) {
        mXmlLang = xmlLang;
    }

    public Direction getDir() {
        return mDir;
    }

    public void setDir(Direction dir) {
        mDir = dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplexTextElement)) return false;
        if (!super.equals(o)) return false;

        final ComplexTextElement title = (ComplexTextElement) o;
        return Objects.equal(mDir, title.mDir)
            && Objects.equal(mXmlLang, title.mXmlLang);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), mDir, mXmlLang);
    }

    public static final ClassLoaderCreator<ComplexTextElement> CREATOR = new ClassLoaderCreator<ComplexTextElement>() {
        @Override
        public ComplexTextElement createFromParcel(Parcel source, ClassLoader loader) {
            return new ComplexTextElement(source, loader);
        }

        @Override
        public ComplexTextElement createFromParcel(Parcel source) {
            return new ComplexTextElement(source, null);
        }

        @Override
        public ComplexTextElement[] newArray(int size) {
            return new ComplexTextElement[size];
        }
    };

    @Override
    protected String getElementName() {
        return OEBContract.Elements.COMPLEX_TEXT_ELEMENT;
    }

    @Override
    protected void onParseAtrributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAtrributes(parser);
        mXmlLang = parser.getAttributeValue(OEBContract.Namespaces.XML, OEBContract.Attributes.LANG);
        mDir = Direction.fromValue(parser.getAttributeValue("", OEBContract.Attributes.DIR));
    }
}
