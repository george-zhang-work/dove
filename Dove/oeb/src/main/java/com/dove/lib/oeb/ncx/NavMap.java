package com.dove.lib.oeb.ncx;

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
 * Created by george on 5/8/14.
 */
public class NavMap extends SimpleElement {
    @SerializedName(OEBContract.Elements.NAV_INFOS)
    private final List<NavInfo> mNavInfos;
    @SerializedName(OEBContract.Elements.NAV_LABELS)
    private final List<NavLabel> mNavLabels;
    @SerializedName(OEBContract.Elements.NAV_POINTS)
    private final List<NavPoint> mNavPoints;

    public NavMap() {
        super();
        mNavInfos = Lists.newArrayList();
        mNavLabels = Lists.newArrayList();
        mNavPoints = Lists.newArrayList();
    }

    public NavMap(Parcel in, ClassLoader loader) {
        super(in, loader);
        mNavInfos = in.readArrayList(loader);
        mNavLabels = in.readArrayList(loader);
        mNavPoints = in.readArrayList(loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.NAV_MAP;
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

                    case OEBContract.Elements.NAV_POINT:
                        final NavPoint navPoint = new NavPoint();
                        navPoint.onParse(parser);
                        mNavPoints.add(navPoint);
                        break;
                }

            } else if (eventType == XmlPullParser.END_TAG) {
                switch (parser.getName()) {
                    case OEBContract.Elements.NAV_MAP:
                        return;
                }

            }
            eventType = parser.next();
        }
    }

    public static final ClassLoaderCreator<NavMap> CREATOR = new ClassLoaderCreator<NavMap>() {
        @Override
        public NavMap createFromParcel(Parcel source, ClassLoader loader) {
            return new NavMap(source, loader);
        }

        @Override
        public NavMap createFromParcel(Parcel source) {
            return new NavMap(source, null);
        }

        @Override
        public NavMap[] newArray(int size) {
            return new NavMap[size];
        }
    };
}
