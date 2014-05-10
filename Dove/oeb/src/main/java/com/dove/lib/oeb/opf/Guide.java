package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.Element;
import com.dove.lib.oeb.OEBContract;
import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

/**
 * Created by george on 5/6/14.
 */
public class Guide extends Element {

    @SerializedName(OEBContract.Elements.REFERENCES)
    private final List<Reference> mReferences;

    public Guide() {
        mReferences = Lists.newArrayList();
    }

    public Guide(Parcel in, ClassLoader loader) {
        super(in, loader);
        mReferences = in.readArrayList(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeList(mReferences);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.GUIDE;
    }

    @Override
    protected void onParseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG) {
                switch (parser.getName()) {

                    case OEBContract.Elements.ITEM_REF:
                        final Reference reference = new Reference();
                        reference.onParse(parser);
                        mReferences.add(reference);
                        break;
                }

            } else if (eventType == XmlPullParser.END_TAG) {
                switch (parser.getName()) {
                    case OEBContract.Elements.GUIDE:
                        return;
                }

            }
            eventType = parser.next();
        }
    }
}
