package com.dove.reader.formatter.oeb;

import com.dove.reader.formatter.XmlParserWrapper;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by george on 4/29/14.
 */
public class OPFGuideParser extends XmlParserWrapper {

    private final XmlParserWrapper mParserDecorator;

    OPFGuideParser(XmlParserWrapper parserDecorator) {
        super(parserDecorator);
        mParserDecorator = parserDecorator;
    }

    @Override
    public void parse(InputStream inputStream) throws XmlPullParserException, IOException {
        mParserDecorator.parse(inputStream);
    }
}
