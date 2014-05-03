package com.dove.reader.formatter;

import com.dove.common.log.LogTag;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by george on 4/26/14.
 */
public abstract class XMLParser {
    protected static final String LOG_TAG = LogTag.getLogTag();

    protected String getInputEncoding() {
        return "utf-8";
    }

    public abstract void parse(InputStream inputStream) throws XmlPullParserException, IOException;

    public void parse(XmlPullParser parser) throws XmlPullParserException, IOException {
    }

    public void serialize() {
    }
}
