package com.dove.reader.formatter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

public abstract class XmlParserWrapper extends XMLParser {
    protected XmlParserWrapper mWrapperParser;

    protected XmlParserWrapper(XmlParserWrapper parserDecorator) {
        mWrapperParser = parserDecorator;
    }

    @Override
    public void parse(InputStream inputStream) throws XmlPullParserException, IOException {
        mWrapperParser.parse(inputStream);
    }

    @Override
    public void parse(XmlPullParser parser) throws XmlPullParserException, IOException {
        mWrapperParser.parse(parser);
    }

    public boolean startDocument(final XmlPullParser parser) throws IOException, XmlPullParserException {
        return mWrapperParser.startDocument(parser);
    }

    public boolean endDocument(final XmlPullParser parser) throws IOException, XmlPullParserException {
        return mWrapperParser.endDocument(parser);
    }

    public boolean startTag(final XmlPullParser parser) throws IOException, XmlPullParserException {
        return mWrapperParser.startTag(parser);
    }

    public boolean endTag(final XmlPullParser parser) throws IOException, XmlPullParserException {
        return mWrapperParser.endTag(parser);
    }

    public boolean canParse(final XmlPullParser parser) throws IOException, XmlPullParserException {
        if (canParse(parser)) {
            parse(parser);
        }
        return mWrapperParser.canParse(parser);
    }
}
