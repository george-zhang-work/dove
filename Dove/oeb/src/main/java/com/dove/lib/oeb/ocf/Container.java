package com.dove.lib.oeb.ocf;

import android.os.Parcel;

import com.dove.lib.oeb.Element;
import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.ParcelableCreator;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

/**
 * Created by george on 5/12/14.
 */
public class Container extends Element {

    @SerializedName(OEBContract.Attributes.VERSION)
    private String mVersion;
    @SerializedName(OEBContract.Elements.ROOT_FILES)
    private RootFiles mRootFiles;

    public Container() {
        super();
    }

    public Container(Parcel in, ClassLoader loader) {
        super(in, loader);
        mVersion = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    public static final ClassLoaderCreator<Container> CREATOR = new ParcelableCreator<>(Container.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.CONTAINER;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.CONTAINER;
    }

    @Override
    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
        mVersion = parser.getAttributeValue("", OEBContract.Attributes.VERSION);
    }

    @Override
    protected void onParseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG) {
                switch (parser.getName()) {
                    case OEBContract.Elements.ROOT_FILES:
                        mRootFiles = new RootFiles();
                        mRootFiles.onParse(parser);
                        break;
                }
            } else if (eventType == XmlPullParser.END_TAG) {
                switch (parser.getName()) {
                    case OEBContract.Elements.CONTAINER:
                        return;
                }
            }
            eventType = parser.next();
        }
    }

    @Override
    protected void setPrefix(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.setPrefix(serializer);
        serializer.setPrefix(OEBContract.Namespaces.Prefix.ANY, OEBContract.Namespaces.CONTAINER);
    }

    @Override
    protected void onSerializeAttributes(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeAttributes(serializer);
        serializeValue(serializer, "", OEBContract.Attributes.VERSION, mVersion);
    }

    @Override
    protected void onSerializeContent(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeContent(serializer);
        serialize(serializer, mRootFiles);
    }
}
