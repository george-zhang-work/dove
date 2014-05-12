package com.dove.lib.oeb.ncx;

import android.os.Parcel;

import com.dove.lib.oeb.Direction;
import com.dove.lib.oeb.Element;
import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.ParcelableCreator;
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
public class NCX extends Element {
    @SerializedName(OEBContract.Attributes.VERSION)
    private String mVersion;
    @SerializedName(OEBContract.Attributes.XML_LANG)
    private String mLang;
    @SerializedName(OEBContract.Attributes.DIR)
    private Direction mDir;

    @SerializedName(OEBContract.Elements.HEAD)
    private Head mHead;
    @SerializedName(OEBContract.Elements.DOC_TITLE)
    private DocTitle mTitle;
    @SerializedName(OEBContract.Elements.DOC_AUTHOR)
    private final List<DocAuthor> mAuthors;
    @SerializedName(OEBContract.Elements.NAV_MAP)
    private NavMap mNavMap;
    @SerializedName(OEBContract.Elements.PAGE_LIST)
    private final PageList mPageList;
    @SerializedName(OEBContract.Elements.NAV_LIST)
    private final NavList mNavList;

    public NCX() {
        super();
        mDir = Direction.EMPTY;

        mAuthors = Lists.newArrayList();
        mNavMap = new NavMap();
        mPageList = new PageList();
        mNavList = new NavList();
    }

    public NCX(Parcel in, ClassLoader loader) {
        super(in, loader);
        mVersion = in.readString();
        mLang = in.readString();
        mDir = Direction.fromValue(in.readString());

        mHead = in.readParcelable(loader);
        mTitle = in.readParcelable(loader);
        mAuthors = in.readArrayList(loader);
        mNavMap = in.readParcelable(loader);
        mPageList = in.readParcelable(loader);
        mNavList = in.readParcelable(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mVersion);
        dest.writeString(mLang);
        dest.writeString(mDir.toString());

        dest.writeParcelable(mHead, flags);
        dest.writeParcelable(mTitle, flags);
        dest.writeList(mAuthors);
        dest.writeParcelable(mNavMap, flags);
        dest.writeParcelable(mPageList, flags);
        dest.writeParcelable(mNavList, flags);
    }

    public static final ClassLoaderCreator<NCX> CREATOR = new ParcelableCreator<>(NCX.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.NCX;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.NCX;
    }

    @Override
    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
        mVersion = parser.getAttributeValue("", OEBContract.Attributes.VERSION);
        mLang = parser.getAttributeValue(OEBContract.Namespaces.XML, OEBContract.Attributes.LANG);
        mDir = Direction.fromValue(parser.getAttributeValue("", OEBContract.Attributes.DIR));
    }

    @Override
    protected void onParseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseContent(parser);
        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {

                switch (parser.getName()) {
                    case OEBContract.Elements.HEAD:
                        mHead = new Head();
                        mHead.onParse(parser);
                        break;

                    case OEBContract.Elements.DOC_TITLE:
                        mTitle = new DocTitle();
                        mTitle.onParse(parser);
                        break;

                    case OEBContract.Elements.DOC_AUTHOR:
                        final DocAuthor author = new DocAuthor();
                        author.onParse(parser);
                        mAuthors.add(author);
                        break;

                    case OEBContract.Elements.NAV_MAP:
                        mNavMap = new NavMap();
                        mNavMap.onParse(parser);
                        break;

                    case OEBContract.Elements.PAGE_LIST:
                        // Remind do nothing, now.
                        break;

                    case OEBContract.Elements.NAV_LIST:
                        // Remind do nothing, now.
                        break;
                }
            } else if (eventType == XmlPullParser.END_TAG) {
                switch (parser.getName()) {
                    case OEBContract.Elements.NCX:
                        return;
                }
            }
            eventType = parser.next();
        }
    }

    @Override
    protected boolean standAlone() {
        return false;
    }

    @Override
    protected void setPrefix(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.setPrefix(serializer);
        serializer.setPrefix(OEBContract.Namespaces.Prefix.ANY, OEBContract.Namespaces.NCX);
    }

    @Override
    protected void onSerializeDocType(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeDocType(serializer);
        serializer.docdecl(" ncx PUBLIC \"-//NISO//DTD ncx 2005-1//EN\"\n" +
            " \"http://www.daisy.org/z3986/2005/ncx-2005-1.dtd\"");

    }

    @Override
    protected void onSerializeAttributes(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeAttributes(serializer);
        serializeValue(serializer, "", OEBContract.Attributes.VERSION, mVersion);
        serializeValue(serializer, OEBContract.Namespaces.XML, OEBContract.Attributes.LANG, mLang);
        serializeValue(serializer, "", OEBContract.Attributes.DIR, mDir.toString());
    }

    @Override
    protected void onSerializeContent(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeContent(serializer);
        serialize(serializer, mHead);
        serialize(serializer, mTitle);
        serializeCollection(serializer, mAuthors);
        serialize(serializer, mNavMap);
        serialize(serializer, mPageList);
        serialize(serializer, mNavList);
    }
}
