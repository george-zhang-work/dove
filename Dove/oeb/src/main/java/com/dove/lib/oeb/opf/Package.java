package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.Direction;
import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.SimpleElement;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by george on 5/6/14.
 */
public class Package extends SimpleElement {

    @SerializedName(OEBContract.Attributes.VERSION)
    private String mVersion;
    @SerializedName(OEBContract.Attributes.UNIQUE_IDENTIFIER)
    private String mUniqueIdentifier;
    @SerializedName(OEBContract.Attributes.PREFIX)
    private String mPrefix;
    @SerializedName(OEBContract.Attributes.XML_LANG)
    private String mXmlLang;
    @SerializedName(OEBContract.Attributes.DIR)
    private Direction mDir;

    @SerializedName(OEBContract.Elements.METADATA)
    private Metadata mMetadata;
    @SerializedName(OEBContract.Elements.MANIFEST)
    private Manifest mManifest;
    @SerializedName(OEBContract.Elements.SPINE)
    private Spine mSpine;
    @SerializedName(OEBContract.Elements.GUIDE)
    private Guide mGuide;
    @SerializedName(OEBContract.Elements.BINDINGS)
    private Bindings mBindings;
    @SerializedName(OEBContract.Elements.TOURS)
    private Tours mTours;

    public Package() {
    }

    public Package(Parcel in, ClassLoader loader) {
        super(in, loader);
        mVersion = in.readString();
        mUniqueIdentifier = in.readString();
        mPrefix = in.readString();
        mXmlLang = in.readString();
        mDir = Direction.fromValue(in.readString());

        mMetadata = in.readParcelable(loader);
        mManifest = in.readParcelable(loader);
        mSpine = in.readParcelable(loader);
        mGuide = in.readParcelable(loader);
        mBindings = in.readParcelable(loader);
        mTours = in.readParcelable(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mVersion);
        dest.writeString(mUniqueIdentifier);
        dest.writeString(mPrefix);
        dest.writeString(mXmlLang);
        dest.writeString(mDir.toString());

        dest.writeParcelable(mMetadata, flags);
        dest.writeParcelable(mManifest, flags);
        dest.writeParcelable(mSpine, flags);
        dest.writeParcelable(mGuide, flags);
        dest.writeParcelable(mBindings, flags);
        dest.writeParcelable(mTours, flags);
    }

    public static final ClassLoaderCreator<Package> CREATOR = new ClassLoaderCreator<Package>() {
        @Override
        public Package createFromParcel(Parcel source, ClassLoader loader) {
            return new Package(source, loader);
        }

        @Override
        public Package createFromParcel(Parcel source) {
            return new Package(source, null);
        }

        @Override
        public Package[] newArray(int size) {
            return new Package[size];
        }
    };

    @Override
    protected String getElementName() {
        return OEBContract.Elements.PACKAGE;
    }

    @Override
    protected void onParseAtrributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAtrributes(parser);
        mVersion = parser.getAttributeValue("", OEBContract.Attributes.VERSION);
        mXmlLang = parser.getAttributeValue(OEBContract.Namespaces.XML, OEBContract.Attributes.LANG);
        mUniqueIdentifier = parser.getAttributeValue("", OEBContract.Attributes.UNIQUE_IDENTIFIER);
        mDir = Direction.fromValue(parser.getAttributeValue("", OEBContract.Attributes.DIR));
        mPrefix = parser.getAttributeValue("", OEBContract.Attributes.PREFIX);
    }

    @Override
    protected void onParseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG) {
                switch (parser.getName()) {

                    case OEBContract.Elements.METADATA:
                        mMetadata = new Metadata();
                        mMetadata.onParse(parser);
                        break;

                    case OEBContract.Elements.MANIFEST:
                        mManifest = new Manifest();
                        mManifest.onParse(parser);
                        break;

                    case OEBContract.Elements.SPINE:
                        mSpine = new Spine();
                        mSpine.onParse(parser);
                        break;

                    case OEBContract.Elements.GUIDE:
                        mGuide = new Guide();
                        mGuide.onParse(parser);
                        break;

                    case OEBContract.Elements.BINDINGS:
                        mBindings = new Bindings();
                        mBindings.onParse(parser);
                        break;

                    case OEBContract.Elements.TOURS:
                        mTours = new Tours();
                        mTours.onParse(parser);
                        break;
                }

            } else if (eventType == XmlPullParser.END_TAG) {
                switch (parser.getName()) {
                    case OEBContract.Elements.PACKAGE:
                        return;
                }

            }
            eventType = parser.next();
        }
    }
}