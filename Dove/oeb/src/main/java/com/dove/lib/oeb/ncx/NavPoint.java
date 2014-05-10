package com.dove.lib.oeb.ncx;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.SimpleTextElement;
import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

/**
 * Created by george on 5/8/14.
 */
public class NavPoint extends SimpleTextElement {
    @SerializedName(OEBContract.Attributes.CLASS)
    private String mClass;
    @SerializedName(OEBContract.Attributes.PLAY_ORDER)
    private int mPlayOrder;

    @SerializedName(OEBContract.Elements.NAV_LABELS)
    private final List<NavLabel> mNavLabels;
    @SerializedName(OEBContract.Elements.CONTENT)
    private Content mContent;
    @SerializedName(OEBContract.Elements.NAV_POINTS)
    private final List<NavPoint> mNavPoints;

    public NavPoint() {
        super();
        mNavLabels = Lists.newArrayList();
        mNavPoints = Lists.newArrayList();
    }

    public NavPoint(Parcel in, ClassLoader loader) {
        super(in, loader);
        mClass = in.readString();
        mPlayOrder = in.readInt();
        mNavLabels = in.readArrayList(loader);
        mContent = in.readParcelable(loader);
        mNavPoints = in.readArrayList(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mClass);
        dest.writeInt(mPlayOrder);
        dest.writeList(mNavLabels);
        dest.writeParcelable(mContent, flags);
        dest.writeList(mNavPoints);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.NAV_POINT;
    }

    @Override
    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
        mClass = parser.getAttributeValue("", OEBContract.Attributes.CLASS);
        mPlayOrder = Integer.parseInt(parser.getAttributeValue("", OEBContract.Attributes.PLAY_ORDER));
    }

    @Override
    protected void onParseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG) {
                switch (parser.getName()) {

                    case OEBContract.Elements.NAV_LABEL:
                        final NavLabel navLabel = new NavLabel();
                        navLabel.onParse(parser);
                        mNavLabels.add(navLabel);
                        break;

                    case OEBContract.Elements.CONTENT:
                        mContent = new Content();
                        mContent.onParse(parser);
                        break;

                    case OEBContract.Elements.NAV_POINT:
                        final NavPoint navPoint = new NavPoint();
                        navPoint.onParse(parser);
                        mNavPoints.add(navPoint);
                        break;
                }

            } else if (eventType == XmlPullParser.END_TAG) {
                switch (parser.getName()) {
                    case OEBContract.Elements.NAV_POINT:
                        return;
                }

            }
            eventType = parser.next();
        }
    }

    public static final ClassLoaderCreator<NavPoint> CREATOR = new ClassLoaderCreator<NavPoint>() {
        @Override
        public NavPoint createFromParcel(Parcel source, ClassLoader loader) {
            return new NavPoint(source, loader);
        }

        @Override
        public NavPoint createFromParcel(Parcel source) {
            return new NavPoint(source, null);
        }

        @Override
        public NavPoint[] newArray(int size) {
            return new NavPoint[size];
        }
    };
}
