package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.SimpleTextElement;

public class Identifier extends SimpleTextElement {
    public Identifier() {
        super();
    }

    public Identifier(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.IDENTIFIER;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
    }

    public static final ClassLoaderCreator<Identifier> CREATOR = new ClassLoaderCreator<Identifier>() {
        @Override
        public Identifier createFromParcel(Parcel source, ClassLoader loader) {
            return new Identifier(source, loader);
        }

        @Override
        public Identifier createFromParcel(Parcel source) {
            return new Identifier(source, null);
        }

        @Override
        public Identifier[] newArray(int size) {
            return new Identifier[size];
        }
    };
}
