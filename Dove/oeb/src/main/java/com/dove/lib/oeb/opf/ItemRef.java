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
 * Created by george on 5/6/14.
 */
public class ItemRef extends SimpleElement {
    @SerializedName(OEBContract.Attributes.IDREF)
    private String mIdRef;
    @SerializedName(OEBContract.Attributes.LINEAR)
    private Linear mLinear;
    @SerializedName(OEBContract.Attributes.PROPERTIES)
    private String mProperties;

    public ItemRef() {
        super();
    }

    public ItemRef(Parcel in, ClassLoader loader) {
        super(in, loader);
        mIdRef = in.readString();
        mLinear = Linear.fromValue(in.readString());
        mProperties = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mIdRef);
        dest.writeString(mLinear.toString());
        dest.writeString(mProperties);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemRef)) return false;

        final ItemRef itemRef = (ItemRef) o;
        return Objects.equal(mIdRef, itemRef.mIdRef);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mIdRef);
    }

    public static final ClassLoaderCreator<ItemRef> CREATOR = new ParcelableCreator<>(ItemRef.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.ITEM_REF;
    }

    @Override
    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
        mIdRef = parser.getAttributeValue("", OEBContract.Attributes.IDREF);
        mLinear = Linear.fromValue(parser.getAttributeValue("", OEBContract.Attributes.LINEAR));
        mProperties = parser.getAttributeValue("", OEBContract.Attributes.PROPERTIES);
    }

    @Override
    protected void onSerializeAttributes(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeAttributes(serializer);
        serializeValue(serializer, "", OEBContract.Attributes.IDREF, mIdRef);
        serializeValue(serializer, "", OEBContract.Attributes.LINEAR, mLinear.toString());
        serializeValue(serializer, "", OEBContract.Attributes.PROPERTIES, mProperties);
    }
}
