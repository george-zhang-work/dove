package com.dove.reader.provider.book;

import android.os.Parcel;

/**
 * Created by george on 5/2/14.
 */
public class Package extends Element {

    private final String mVersion;
    private final String mUniqueIdentifier;
    private final String mPrefix;
    private final String mXmlLang;
    private final Direction mDir;

    private final Metadata mMetadata;
    private final Manifest mManifest;
    private final Spine mSpine;
    private final Guide mGuide;
    private final Bindings mBindings;

    public Package(String id, String version, String uniqueIdentifier, String prefix,
                   String xmlLang, Direction dir) {
        super(id);
        mVersion = version;
        mUniqueIdentifier = uniqueIdentifier;
        mPrefix = prefix;
        mXmlLang = xmlLang;
        mDir = dir;
        mMetadata = new Metadata();
        mManifest = new Manifest();
        mSpine = new Spine();
        mGuide = new Guide();
        mBindings = new Bindings();
    }

    public Package(Parcel in, ClassLoader loader) {
        super(in, loader);
        mVersion = in.readString();
        mUniqueIdentifier = in.readString();
        mPrefix = in.readString();
        mXmlLang = in.readString();
        mDir = Direction.fromValue(in.readString());
        mMetadata = in.readParcelable(loader);
        mManifest = in.readParcelable(loader);
        mSpine = in.readParcelable(loader);
        mGuide = in.readParcelable(loader);
        mBindings = in.readParcelable(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }
}
