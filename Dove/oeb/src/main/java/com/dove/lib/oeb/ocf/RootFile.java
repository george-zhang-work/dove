package com.dove.lib.oeb.ocf;

import android.os.Parcel;

import com.dove.lib.oeb.Element;
import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.ParcelableCreator;
import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

/**
 * Created by george on 5/12/14.
 */
public class RootFile extends Element {
    @SerializedName(OEBContract.Attributes.FULL_PATH)
    private String mFullPath;
    @SerializedName(OEBContract.Attributes.MEDIA_TYPE)
    private String mMediaType;

    public RootFile() {
        super();
    }

    public RootFile(Parcel in, ClassLoader loader) {
        super(in, loader);
        mFullPath = in.readString();
        mMediaType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mFullPath);
        dest.writeString(mMediaType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RootFile)) return false;
        final RootFile rootFile = (RootFile) o;
        return Objects.equal(mFullPath, rootFile.mFullPath);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mFullPath);
    }

    public static final ClassLoaderCreator<RootFile> CREATOR = new ParcelableCreator<>(RootFile.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.ROOT_FILE;
    }

    @Override
    protected void onParseAttributes(XmlPullParser parser)
        throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
        mFullPath = parser.getAttributeValue("", OEBContract.Attributes.FULL_PATH);
        mMediaType = parser.getAttributeValue("", OEBContract.Attributes.MEDIA_TYPE);
    }

    @Override
    protected void onSerializeAttributes(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeAttributes(serializer);
        serializeValue(serializer, "", OEBContract.Attributes.FULL_PATH, mFullPath);
        serializeValue(serializer, "", OEBContract.Attributes.MEDIA_TYPE, mMediaType);
    }
}
