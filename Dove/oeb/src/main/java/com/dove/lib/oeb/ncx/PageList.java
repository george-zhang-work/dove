package com.dove.lib.oeb.ncx;

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
 * Created by george on 5/8/14.
 */
public class PageList extends SimpleElement {
    @SerializedName(OEBContract.Attributes.CLASS)
    private String mClass;

    @SerializedName(OEBContract.Elements.NAV_INFOS)
    private final List<NavInfo> mNavInfos;
    @SerializedName(OEBContract.Elements.NAV_LABELS)
    private final List<NavLabel> mNavLabels;
    @SerializedName(OEBContract.Elements.NAV_POINTS)
    private final List<PageTarget> mPageTargets;

    public PageList() {
        super();
        mNavInfos = Lists.newArrayList();
        mNavLabels = Lists.newArrayList();
        mPageTargets = Lists.newArrayList();
    }

    public PageList(Parcel in, ClassLoader loader) {
        super(in, loader);
        mClass = in.readString();
        mNavInfos = in.readArrayList(loader);
        mNavLabels = in.readArrayList(loader);
        mPageTargets = in.readArrayList(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mClass);
        dest.writeList(mNavInfos);
        dest.writeList(mNavLabels);
        dest.writeList(mPageTargets);
    }

    public static final ClassLoaderCreator<PageList> CREATOR = new ParcelableCreator<>(PageList.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.PAGE_LIST;
    }

    @Override
    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
        mClass = parser.getAttributeValue("", OEBContract.Attributes.CLASS);
    }

    @Override
    protected void onParseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG) {
                switch (parser.getName()) {

                    case OEBContract.Elements.NAV_INFO:
                        final NavInfo navInfo = new NavInfo();
                        navInfo.onParse(parser);
                        mNavInfos.add(navInfo);
                        break;

                    case OEBContract.Elements.NAV_LABEL:
                        final NavLabel navLabel = new NavLabel();
                        navLabel.onParse(parser);
                        mNavLabels.add(navLabel);
                        break;

                    case OEBContract.Elements.PAGE_TARGET:
                        final PageTarget pageTarget = new PageTarget();
                        mPageTargets.add(pageTarget);
                        break;
                }

            } else if (eventType == XmlPullParser.END_TAG) {
                switch (parser.getName()) {
                    case OEBContract.Elements.PAGE_LIST:
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
        serializeValue(serializer, "", OEBContract.Attributes.CLASS, mClass);
    }

    @Override
    protected void onSerializeContent(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeContent(serializer);
        serializeCollection(serializer, mNavInfos);
        serializeCollection(serializer, mNavLabels);
        serializeCollection(serializer, mPageTargets);
    }
}
