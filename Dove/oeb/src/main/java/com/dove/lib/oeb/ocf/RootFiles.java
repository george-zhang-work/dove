package com.dove.lib.oeb.ocf;

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
 * Created by george on 5/12/14.
 */
public class RootFiles extends Element {
    @SerializedName(OEBContract.Elements.ROOT_FILES)
    private final List<RootFile> mRootFiles;

    public RootFiles() {
        super();
        mRootFiles = Lists.newArrayList();
    }

    public RootFiles(Parcel in, ClassLoader loader) {
        super(in, loader);
        mRootFiles = in.readArrayList(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeList(mRootFiles);
    }

    public static final ClassLoaderCreator<RootFiles> CREATOR = new ParcelableCreator<>(RootFiles.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.ROOT_FILES;
    }

    @Override
    protected void onParseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG) {
                switch (parser.getName()) {
                    case OEBContract.Elements.ROOT_FILE:
                        final RootFile rootFile = new RootFile();
                        rootFile.onParse(parser);
                        mRootFiles.add(rootFile);
                        break;
                }

            } else if (eventType == XmlPullParser.END_TAG) {
                switch (parser.getName()) {
                    case OEBContract.Elements.ROOT_FILES:
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
        serializeCollection(serializer, mRootFiles);
    }
}
