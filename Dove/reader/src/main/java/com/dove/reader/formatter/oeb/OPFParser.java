package com.dove.reader.formatter.oeb;

import android.util.Xml;

import com.dove.reader.formatter.XmlParserWrapper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by george on 4/29/14.
 */
public final class OPFParser extends XmlParserWrapper {

    public OPFParser() {
        this(null);
        mWrapperParser = new OPFMetadataParser(this);
        mWrapperParser = new OPFManifestParser(mWrapperParser);
        mWrapperParser = new OPFSpineParser(mWrapperParser);
        mWrapperParser = new OPFGuideParser(mWrapperParser);
    }

    protected OPFParser(XmlParserWrapper parserDecorator) {
        super(parserDecorator);
    }

    @Override
    public void parse(InputStream inputStream) throws XmlPullParserException, IOException {
        final XmlPullParser parser = Xml.newPullParser();
        parser.setInput(inputStream, getInputEncoding());

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    mWrapperParser.startDocument(parser);
                    break;

                case XmlPullParser.END_DOCUMENT:
                    mWrapperParser.startDocument(parser);
                    break;

                case XmlPullParser.START_TAG:
                    mWrapperParser.startTag(parser);
                    break;

                case XmlPullParser.END_TAG:
                    mWrapperParser.endTag(parser);
                    break;
            }
            eventType = parser.next();
        }

    }

    @Override
    public boolean canParse(XmlPullParser parser) throws IOException, XmlPullParserException {
        // Default, can't parse.
        return false;
    }

    @Override
    public void parse(XmlPullParser parser) throws XmlPullParserException, IOException {
        // Default, do nothing here.
    }

    @Override
    public boolean startDocument(XmlPullParser parser) {
        // Default.
        return true;
    }

    @Override
    public boolean endDocument(XmlPullParser parser) {
        // Default.
        return true;
    }

    @Override
    public boolean startTag(XmlPullParser parser) {
        // Default.
        return true;
    }

    @Override
    public boolean endTag(XmlPullParser parser) {
        // Default.
        return true;
    }
}
