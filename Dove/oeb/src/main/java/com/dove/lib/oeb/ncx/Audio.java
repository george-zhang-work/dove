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

    public static final ClassLoaderCreator<Audio> CREATOR = new ParcelableCreator<>(Audio.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.AUDIO;
    }

    @Override
    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
        mSrc = parser.getAttributeValue("", OEBContract.Attributes.SRC);
        mClipBegin = parser.getAttributeValue("", OEBContract.Attributes.CLIP_BEGIN);
        mClipEnd = parser.getAttributeValue("", OEBContract.Attributes.CLIP_END);
    }

    @Override
    protected void onSerializeAttributes(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeAttributes(serializer);
        serializeValue(serializer, "", OEBContract.Attributes.SRC, mSrc);
        serializeValue(serializer, "", OEBContract.Attributes.CLIP_BEGIN, mClipBegin);
        serializeValue(serializer, "", OEBContract.Attributes.CLIP_END, mClipEnd);
    }
}
