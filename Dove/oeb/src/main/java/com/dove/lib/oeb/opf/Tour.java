package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.SimpleElement;
import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

/**
 * Created by george on 5/7/14.
 */
public class Tour extends SimpleElement {

    @SerializedName(OEBContract.Attributes.TITLE)
    private String mTitle;

    @SerializedName(OEBContract.Attributes.SITES)
    private final List<Site> mSites;

    public Tour() {
        super();
        mSites = Lists.newArrayList();
    }

    public Tour(Parcel in, ClassLoader loader) {
        super(in, loader);
        mTitle = in.readString();
        mSites = in.readArrayList(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mTitle);
        dest.writeList(mSites);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.TOUR;
    }

    @Override
    protected void onParseAtrributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAtrributes(parser);
        mTitle = parser.getAttributeValue("", OEBContract.Attributes.TITLE);
    }

    @Override
    protected void onParseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG) {
                switch (parser.getName()) {

                    case OEBContract.Elements.SITE:
                        final Site site = new Site();
                        site.onParse(parser);
                        mSites.add(site);
                        break;
                }

            } else if (eventType == XmlPullParser.END_TAG) {
                switch (parser.getName()) {
                    case OEBContract.Elements.TOUR:
                        return;
                }

            }
            eventType = parser.next();
        }
    }

    public static final ClassLoaderCreator<Tour> CREATOR = new ClassLoaderCreator<Tour>() {
        @Override
        public Tour createFromParcel(Parcel source, ClassLoader loader) {
            return new Tour(source, loader);
        }

        @Override
        public Tour createFromParcel(Parcel source) {
            return new Tour(source, null);
        }

        @Override
        public Tour[] newArray(int size) {
            return new Tour[size];
        }
    };

}
