package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.SimpleTextElement;

public class Format extends SimpleTextElement {
    public Format() {
        super();
    }

    public Format(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.FORMAT;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
    }

    public static final ClassLoaderCreator<Format> CREATOR = new ClassLoaderCreator<Format>() {
        @Override
        public Format createFromParcel(Parcel source, ClassLoader loader) {
            return new Format(source, loader);
        }

        @Override
        public Format createFromParcel(Parcel source) {
            return new Format(source, null);
        }

        @Override
        public Format[] newArray(int size) {
            return new Format[size];
        }
    };
}
