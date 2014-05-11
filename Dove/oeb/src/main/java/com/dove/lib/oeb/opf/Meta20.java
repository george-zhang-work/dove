package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.ParcelableCreator;
import com.dove.lib.oeb.SimpleElement;
import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

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

    public static final ClassLoaderCreator<Meta20> CREATOR = new ParcelableCreator<>(Meta20.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.META;
    }

    @Override
    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
        mXmlLang = parser.getAttributeValue(OEBContract.Namespaces.XML, OEBContract.Attributes.LANG);
        mName = parser.getAttributeValue("", OEBContract.Attributes.NAME);
        mContent = parser.getAttributeValue("", OEBContract.Attributes.CONTENT);
        mScheme = parser.getAttributeValue("", OEBContract.Attributes.SCHEME);
    }

    @Override
    protected void onSerializeAttributes(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeAttributes(serializer);
        serializeValue(serializer, OEBContract.Namespaces.XML, OEBContract.Attributes.LANG, mXmlLang);
        serializeValue(serializer, "", OEBContract.Attributes.NAME, mName);
        serializeValue(serializer, "", OEBContract.Attributes.CONTENT, mContent);
        serializeValue(serializer, "", OEBContract.Attributes.SCHEME, mScheme);
    }
}
