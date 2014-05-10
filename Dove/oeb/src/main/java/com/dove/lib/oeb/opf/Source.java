package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.SimpleTextElement;

public class Source extends SimpleTextElement {
    public Source() {
        super();
    }

    public Source(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.SOURCE;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
    }

    public static final ClassLoaderCreator<Source> CREATOR = new ClassLoaderCreator<Source>() {
        @Override
        public Source createFromParcel(Parcel source, ClassLoader loader) {
            return new Source(source, loader);
        }

        @Override
        public Source createFromParcel(Parcel source) {
            return new Source(source, null);
        }

        @Override
        public Source[] newArray(int size) {
            return new Source[size];
        }
    };
}
