package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.SimpleElement;
import com.google.common.collect.Maps;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created by george on 5/6/14.
 */
public class Manifest extends SimpleElement {

    @SerializedName(OEBContract.Elements.RESOURCES)
    private final LinkedHashMap<String, Item> mResources;

    public Manifest() {
        mResources = Maps.newLinkedHashMap();
    }

    public Manifest(Parcel in, ClassLoader loader) {
        super(in, loader);
        mResources = Maps.newLinkedHashMap();
        in.readMap(mResources, loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeMap(mResources);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.MANIFEST;
    }

    @Override
    protected void onParseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG) {
                switch (parser.getName()) {
                    case OEBContract.Elements.ITEM:
                        final Item item = new Item();
                        item.onParse(parser);
                        mResources.put(item.getHref(), item);
                        break;
                }

            } else if (eventType == XmlPullParser.END_TAG) {
                switch (parser.getName()) {
                    case OEBContract.Elements.MANIFEST:
                        return;
                }
            }
            eventType = parser.next();
        }
    }
}
