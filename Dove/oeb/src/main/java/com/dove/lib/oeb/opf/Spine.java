package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.Direction;
import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.SimpleElement;
import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

/**
 * Created by george on 5/6/14.
 */
public class Spine extends SimpleElement {
    @SerializedName(OEBContract.Attributes.TOC)
    private String mToc;

    @SerializedName(OEBContract.Attributes.PAGE_PROGRESSION_DIRECTION)
    private Direction mDir;

    @SerializedName(OEBContract.Elements.ITEM_REFS)
    private final List<ItemRef> mItemRefs;

    public Spine() {
        mItemRefs = Lists.newArrayList();
    }

    public Spine(Parcel in, ClassLoader loader) {
        super(in, loader);
        mToc = in.readString();
        mDir = Direction.fromValue(in.readString());
        mItemRefs = in.readArrayList(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mToc);
        dest.writeString(mDir.toString());
        dest.writeList(mItemRefs);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.SPINE;
    }

    @Override
    protected void onParseAtrributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAtrributes(parser);
        mToc = parser.getAttributeValue("", OEBContract.Attributes.TOC);
        mDir = Direction.fromValue(parser.getAttributeValue("", OEBContract.Attributes.DIR));
    }

    @Override
    protected void onParseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG) {
                switch (parser.getName()) {

                    case OEBContract.Elements.ITEM_REF:
                        final ItemRef itemRef = new ItemRef();
                        itemRef.onParse(parser);
                        mItemRefs.add(itemRef);
                        break;
                }

            } else if (eventType == XmlPullParser.END_TAG) {
                switch (parser.getName()) {
                    case OEBContract.Elements.SPINE:
                        return;
                }

            }
            eventType = parser.next();
        }
    }
}
