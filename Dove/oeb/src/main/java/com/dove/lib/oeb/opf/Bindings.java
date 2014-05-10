package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.Element;
import com.dove.lib.oeb.OEBContract;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

/**
 * Created by george on 5/8/14.
 */
public class Bindings extends Element {
    @SerializedName(OEBContract.Elements.MEDIA_TYPES)
    List<MediaType> mMediaTypes;

    public Bindings() {
        super();
    }

    public Bindings(Parcel in, ClassLoader loader) {
        super(in, loader);
        mMediaTypes = in.readArrayList(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeList(mMediaTypes);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.BINDINGS;
    }

    @Override
    protected void onParseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG) {
                switch (parser.getName()) {

                    case OEBContract.Elements.MEDIA_TYPE:
                        final MediaType mediaType = new MediaType();
                        mediaType.onParse(parser);
                        mMediaTypes.add(mediaType);
                        break;
                }

            } else if (eventType == XmlPullParser.END_TAG) {
                switch (parser.getName()) {
                    case OEBContract.Elements.BINDINGS:
                        return;
                }

            }
            eventType = parser.next();
        }
    }
}
