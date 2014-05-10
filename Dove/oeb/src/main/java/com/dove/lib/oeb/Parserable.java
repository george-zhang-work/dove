package com.dove.lib.oeb;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by george on 5/5/14.
 */
public interface Parserable {

    void onParse(InputStream inputStream) throws XmlPullParserException, IOException;

    void onParse(XmlPullParser parser) throws XmlPullParserException, IOException;
}
