package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.ParcelableCreator;
import com.dove.lib.oeb.SimpleElement;
import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

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

    public static final ClassLoaderCreator<Tour> CREATOR = new ParcelableCreator<>(Tour.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.TOUR;
    }

    @Override
    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
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

    @Override
    protected void onSerializeAttributes(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeAttributes(serializer);
        serializeValue(serializer, "", OEBContract.Attributes.TITLE, mTitle);
    }

    @Override
    protected void onSerializeContent(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeContent(serializer);
        serializeCollection(serializer, mSites);
    }
}
