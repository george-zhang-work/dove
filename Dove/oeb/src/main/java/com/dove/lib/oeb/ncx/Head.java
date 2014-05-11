package com.dove.lib.oeb.ncx;

import android.os.Parcel;

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
public class Head extends Element {
    @SerializedName(OEBContract.Elements.METAS)
    private List<Meta> mMetas;

    public Head() {
        super();
        mMetas = Lists.newArrayList();
    }

    public Head(Parcel in, ClassLoader loader) {
        super(in, loader);
        mMetas = in.readArrayList(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeList(mMetas);
    }

    public static final ClassLoaderCreator<Head> CREATOR = new ParcelableCreator<>(Head.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.HEAD;
    }

    @Override
    protected void onParseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG) {
                switch (parser.getName()) {

                    case OEBContract.Elements.META:
                        final Meta meta = new Meta();
                        meta.onParse(parser);
                        mMetas.add(meta);
                        break;
                }

            } else if (eventType == XmlPullParser.END_TAG) {
                switch (parser.getName()) {
                    case OEBContract.Elements.HEAD:
                        return;
                }

            }
            eventType = parser.next();
        }
    }

    @Override
    protected void onSerializeContent(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeContent(serializer);
        serializeCollection(serializer, mMetas);
    }
}
