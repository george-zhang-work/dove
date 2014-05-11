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
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

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

    public static final ClassLoaderCreator<Element> CREATOR = new ParcelableCreator<>(Element.class);

    protected String getElementName() {
        return OEBContract.Elements.ELEMENT;
    }

    protected String getElementNamespace() {
        return OEBContract.Namespaces.ANY;
    }

    protected String getElementNamespacePrefix() {
        return OEBContract.NamespacePrefix.ANY;
    }

    @Override
    public final void onParse(InputStream inputStream) throws XmlPullParserException, IOException {
        final XmlPullParser parser = Xml.newPullParser();
        parser.setInput(inputStream, getEncoding());
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
        LogUtils.i(LOG_TAG, "%s : %s", parser.getNamespace(), parser.getName());
        parser.require(XmlPullParser.START_TAG, getElementNamespace(), getElementName());
        onParseAttributes(parser);
        onParseContent(parser);
    }

    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
    }

    protected void onParseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
    }

    @Override
    public final void onSrerialize(OutputStream outputStream)
        throws IOException, IllegalArgumentException, IllegalStateException {
        final XmlSerializer serializer = Xml.newSerializer();
        serializer.setOutput(outputStream, getEncoding());
        serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
        serializer.startDocument(getEncoding(), standAlone());
        onSerializeDocType(serializer);
        onSerialize(serializer);
        serializer.endDocument();
        serializer.flush();
    }

    @Override
    public final void onSerialize(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        serializer.startTag(getElementNamespacePrefix(), getElementName());
        onSerializeAttributes(serializer);
        onSerializeContent(serializer);
        serializer.endTag(getElementNamespacePrefix(), getElementName());
        serializer.flush();
    }

    protected void onSerializeDocType(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
    }

    protected void onSerializeAttributes(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
    }

    protected void onSerializeContent(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
    }

    protected void serialize(XmlSerializer serializer, Serializerable serializerable)
        throws IOException, IllegalArgumentException, IllegalStateException {
        if (serializerable != null) {
            serializerable.onSerialize(serializer);
        }
    }

    protected void serializeValue(XmlSerializer serializer, String namespace, String name, String value)
        throws IOException, IllegalArgumentException, IllegalStateException {
        if (!TextUtils.isEmpty(value)) {
            serializer.attribute(namespace, name, value);
        }
    }

    protected void serializeCollection(XmlSerializer serializer, Collection<? extends Serializerable> serializerables)
        throws IOException, IllegalArgumentException, IllegalStateException {
        if (serializerables != null) {
            for (Serializerable serializerable : serializerables) {
                serializerable.onSerialize(serializer);
            }
        }
    }
}
