package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.ParcelableCreator;
import com.dove.lib.oeb.SimpleElement;
import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.util.List;

/**
 * Created by george on 5/7/14.
 */
public class Tours extends SimpleElement {

    @SerializedName(OEBContract.Attributes.SITES)
    private final List<Tour> mTours;

    public Tours() {
        super();
        mTours = Lists.newArrayList();
    }

    public Tours(Parcel in, ClassLoader loader) {
        super(in, loader);
        mTours = in.readArrayList(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeList(mTours);
    }

    public static final ClassLoaderCreator<Tours> CREATOR = new ParcelableCreator<>(Tours.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.TOURS;
    }

    @Override
    protected void onParseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG) {
                switch (parser.getName()) {

                    case OEBContract.Elements.TOUR:
                        final Tour tour = new Tour();
                        tour.onParse(parser);
                        mTours.add(tour);
                        break;
                }

            } else if (eventType == XmlPullParser.END_TAG) {
                switch (parser.getName()) {
                    case OEBContract.Elements.TOURS:
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
        serializeCollection(serializer, mTours);
    }
}
