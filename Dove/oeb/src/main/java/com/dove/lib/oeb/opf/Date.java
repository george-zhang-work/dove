package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.SimpleTextElement;

public class Date extends SimpleTextElement {
    public Date() {
        super();
    }

    public Date(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.DATE;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
    }

    public static final ClassLoaderCreator<Date> CREATOR = new ClassLoaderCreator<Date>() {
        @Override
        public Date createFromParcel(Parcel source, ClassLoader loader) {
            return new Date(source, loader);
        }

        @Override
        public Date createFromParcel(Parcel source) {
            return new Date(source, null);
        }

        @Override
        public Date[] newArray(int size) {
            return new Date[size];
        }
    };
}
