package com.dove.lib.oeb;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Xml;

import com.dove.common.log.LogTag;
import com.dove.common.log.LogUtils;
import com.google.common.base.Objects;
import com.google.gson.GsonBuilder;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by george on 5/5/14.
 */
public class Element implements Parcelable, Serializeable, Parserable {

    protected static final String LOG_TAG = LogTag.getLogTag();

    public Element() {
    }

    public Element(Parcel in, ClassLoader loader) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    protected String getInputEncoding() {
        return "utf-8";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return toSerialize();
    }

    @Override
    public String toSerialize() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }

    protected String getElementName() {
        return OEBContract.Elements.ELEMENT;
    }

    protected String getElementNamespace() {
        return OEBContract.Namespaces.ANY;
    }

    @Override
    public final void onParse(InputStream inputStream) throws XmlPullParserException, IOException {
        final XmlPullParser parser = Xml.newPullParser();
        parser.setInput(inputStream, getInputEncoding());

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            final String name = parser.getName();
            if (!TextUtils.isEmpty(name))
                LogUtils.i(LOG_TAG, name);
            if (eventType == XmlPullParser.START_TAG
                && Objects.equal(getElementName(), parser.getName())) {
                onParse(parser);
                break;
            }
            eventType = parser.next();
        }
    }

    @Override
    public final void onParse(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, getElementNamespace(), getElementName());
        onParseAtrributes(parser);
        onParseContent(parser);
    }

    protected void onParseAtrributes(XmlPullParser parser) throws XmlPullParserException, IOException {
    }

    protected void onParseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
    }
}
