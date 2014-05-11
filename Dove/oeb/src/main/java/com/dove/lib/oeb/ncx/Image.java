package com.dove.lib.oeb.ncx;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.ParcelableCreator;
import com.dove.lib.oeb.SimpleElement;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

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

    public static final ClassLoaderCreator<Image> CREATOR = new ParcelableCreator<>(Image.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.IMAGE;
    }

    @Override
    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
        mSrc = parser.getAttributeValue("", OEBContract.Attributes.SRC);
    }

    @Override
    protected void onSerializeAttributes(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeAttributes(serializer);
        serializeValue(serializer, "", OEBContract.Attributes.SRC, mSrc);
    }
}
