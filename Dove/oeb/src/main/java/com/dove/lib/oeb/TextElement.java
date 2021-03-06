package com.dove.lib.oeb;

import android.os.Parcel;
import android.text.TextUtils;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

/**
 * Created by george on 5/5/14.
 */
public class TextElement extends Element {

    @SerializedName(OEBContract.Elements.TEXT)
    protected String mText;

    public TextElement() {
    }

    public TextElement(Parcel in, ClassLoader loader) {
        mText = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mText);
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextElement)) return false;

        final TextElement that = (TextElement) o;
        return Objects.equal(mText, that.mText);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mText);
    }

    public static final ClassLoaderCreator<TextElement> CREATOR = new ParcelableCreator<>(TextElement.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.TEXT_ELEMENT;
    }

    @Override
    protected void onParseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
        mText = parser.nextText();
    }

    @Override
    protected void onSerializeContent(XmlSerializer serializer) throws IOException {
        super.onSerializeContent(serializer);
        if (!TextUtils.isEmpty(mText)) {
            serializer.text(mText);
        }
    }
}
