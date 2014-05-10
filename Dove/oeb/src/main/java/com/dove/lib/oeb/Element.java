package com.dove.lib.oeb;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Xml;

import com.dove.common.log.LogTag;
import com.google.common.base.Objects;
import com.google.gson.GsonBuilder;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by george on 5/5/14.
 */
public class Element implements Parcelable, Parserable, Serializerable {

    protected static final String LOG_TAG = LogTag.getLogTag();

    public Element() {
    }

    public Element(Parcel in, ClassLoader loader) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    protected String getEncoding() {
        return "utf-8";
    }

    protected boolean standAlone() {
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return toSerialize();
    }

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
        parser.setInput(inputStream, getEncoding());
        Xml.newSerializer();

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
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
        onParseAttributes(parser);
        onParseContent(parser);
    }

    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
    }

    protected void onParseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
    }

    @Override
    public void onSrerialize(OutputStream outputStream) throws IOException {
        final XmlSerializer serializer = Xml.newSerializer();
        serializer.setOutput(outputStream, getEncoding());
        serializer.startDocument(getEncoding(), standAlone());
        onSerializeDocType(serializer);
        onSerialize(serializer);
        serializer.endDocument();
    }

    @Override
    public void onSerialize(XmlSerializer serializer) throws IOException {
        serializer.startTag(getElementNamespace(), getElementName());
        onSerializeAttributes(serializer);
        onSerializeContent(serializer);
        serializer.endTag(getElementNamespace(), getElementName());
    }

    protected void onSerializeDocType(XmlSerializer serializer) {
    }

    protected void onSerializeAttributes(XmlSerializer serializer) {

    }

    private void onSerializeContent(XmlSerializer serializer) {

    }
}
