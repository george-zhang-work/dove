package com.dove.reader.formatter.oeb;

import com.dove.reader.formatter.XmlParserWrapper;
import com.dove.reader.formatter.oeb.OPF.Tags;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 4/29/14.
 */
public class OPFMetadataParser extends XmlParserWrapper {

    private String mTitle;

    private final List<String> mTitles;

    protected OPFMetadataParser(XmlParserWrapper parserDecorator) {
        super(parserDecorator);
        mTitles = new ArrayList<>();
    }

    @Override
    public boolean canParse(XmlPullParser parser) throws IOException, XmlPullParserException {
        return Tags.metadata.equalsIgnoreCase(parser.getName());
    }

    @Override
    public void parse(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    break;

                case XmlPullParser.END_TAG:
                    if (canParse(parser)) {
                        return;
                    }
                    break;
            }
            eventType = parser.next();
        }
    }

    private String getTag(String tagName) {
        final String[] arr = tagName.split(":");
        return arr[arr.length - 1];
    }

    private void onStartTag(XmlPullParser parser) throws IOException, XmlPullParserException {
        final String tagName = parser.getName();
        switch (getTag(tagName)) {
            case Tags.title:
                mTitles.add(parser.nextText());
                break;

            case Tags.creator:

        }
    }
}
