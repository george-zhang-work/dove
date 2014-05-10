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
public class SimpleTextElement extends TextElement {

    @SerializedName(OEBContract.Attributes.ID)
    protected String mId;

    public SimpleTextElement() {
    }

    public SimpleTextElement(Parcel in, ClassLoader loader) {
        super(in, loader);
        mId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mId);
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleTextElement)) return false;
        if (!super.equals(o)) return false;

        final SimpleTextElement that = (SimpleTextElement) o;
        return Objects.equal(mId, that.mId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), mId);
    }

    public static final ClassLoaderCreator<SimpleTextElement> CREATOR = new ClassLoaderCreator<SimpleTextElement>() {
        @Override
        public SimpleTextElement createFromParcel(Parcel source, ClassLoader loader) {
            return new SimpleTextElement(source, loader);
        }

        @Override
        public SimpleTextElement createFromParcel(Parcel source) {
            return new SimpleTextElement(source, null);
        }

        @Override
        public SimpleTextElement[] newArray(int size) {
            return new SimpleTextElement[size];
        }
    };

    @Override
    protected String getElementName() {
        return OEBContract.Elements.SIMPLE_TEXT_ELEMENT;
    }

    @Override
    protected void onParseAtrributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAtrributes(parser);
        mId = parser.getAttributeValue("", mId);
    }
}
