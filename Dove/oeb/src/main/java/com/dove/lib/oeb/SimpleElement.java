package com.dove.lib.oeb;

import android.os.Parcel;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

/**
 * Created by george on 5/5/14.
 */
public class SimpleElement extends Element {
    @SerializedName(OEBContract.Attributes.ID)
    protected String mId;

    public SimpleElement() {
    }

    public SimpleElement(Parcel in, ClassLoader loader) {
        mId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
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
        if (!(o instanceof SimpleElement)) return false;

        final SimpleElement that = (SimpleElement) o;
        return Objects.equal(mId, that.mId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mId);
    }

    public static final ClassLoaderCreator<SimpleElement> CREATOR = new ParcelableCreator<>(SimpleElement.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.SIMPLE_ELEMENT;
    }

    @Override
    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
        mId = parser.getAttributeValue("", OEBContract.Attributes.ID);
    }

    @Override
    protected void onSerializeAttributes(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeAttributes(serializer);
        serializeValue(serializer, "", OEBContract.Attributes.ID, mId);
    }
}
