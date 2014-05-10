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
public class NavTarget extends SimpleTextElement {
    @SerializedName(OEBContract.Attributes.CLASS)
    private String mClass;
    @SerializedName(OEBContract.Attributes.PLAY_ORDER)
    private int mPlayOrder;
    @SerializedName(OEBContract.Attributes.VALUE)
    private String mValue;

    @SerializedName(OEBContract.Elements.NAV_LABELS)
    private List<NavLabel> mNavLabels;
    @SerializedName(OEBContract.Elements.CONTENT)
    private Content mContent;

    public NavTarget() {
        super();
        mNavLabels = Lists.newArrayList();
    }

    public NavTarget(Parcel in, ClassLoader loader) {
        super(in, loader);
        mClass = in.readString();
        mPlayOrder = in.readInt();
        mValue = in.readString();
        mNavLabels = in.readArrayList(loader);
        mContent = in.readParcelable(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mClass);
        dest.writeInt(mPlayOrder);
        dest.writeString(mValue);
        dest.writeList(mNavLabels);
        dest.writeParcelable(mContent, flags);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.NAV_TARGET;
    }

    @Override
    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
        mClass = parser.getAttributeValue("", OEBContract.Attributes.CLASS);
        mPlayOrder = Integer.parseInt(parser.getAttributeValue("", OEBContract.Attributes.PLAY_ORDER));
        mValue = parser.getAttributeValue("", OEBContract.Attributes.VALUE);
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
                }

            } else if (eventType == XmlPullParser.END_TAG) {
                switch (parser.getName()) {
                    case OEBContract.Elements.GUIDE:
                        return;
                }

            }
            eventType = parser.next();
        }
    }

    public static final ClassLoaderCreator<NavTarget> CREATOR = new ClassLoaderCreator<NavTarget>() {
        @Override
        public NavTarget createFromParcel(Parcel source, ClassLoader loader) {
            return new NavTarget(source, loader);
        }

        @Override
        public NavTarget createFromParcel(Parcel source) {
            return new NavTarget(source, null);
        }

        @Override
        public NavTarget[] newArray(int size) {
            return new NavTarget[size];
        }
    };
}
