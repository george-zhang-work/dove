package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.SimpleTextElement;

public class Type extends SimpleTextElement {
    public Type() {
        super();
    }

    public Type(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.TYPE;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
    }

    public static final ClassLoaderCreator<Type> CREATOR = new ClassLoaderCreator<Type>() {
        @Override
        public Type createFromParcel(Parcel source, ClassLoader loader) {
            return new Type(source, loader);
        }

        @Override
        public Type createFromParcel(Parcel source) {
            return new Type(source, null);
        }

        @Override
        public Type[] newArray(int size) {
            return new Type[size];
        }
    };
}
