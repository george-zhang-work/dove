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
public class NavPoint extends SimpleElement {
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

    public static final ClassLoaderCreator<NavPoint> CREATOR = new ParcelableCreator<>(NavPoint.class);

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

    @Override
    protected void onSerializeAttributes(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeAttributes(serializer);
        serializeValue(serializer, "", OEBContract.Attributes.CLASS, mClass);
        serializeValue(serializer, "", OEBContract.Attributes.PLAY_ORDER, String.valueOf(mPlayOrder));
    }

    @Override
    protected void onSerializeContent(XmlSerializer serializer) throws IOException {
        super.onSerializeContent(serializer);
        serializeCollection(serializer, mNavLabels);
        serialize(serializer, mContent);
        serializeCollection(serializer, mNavPoints);
    }
}
