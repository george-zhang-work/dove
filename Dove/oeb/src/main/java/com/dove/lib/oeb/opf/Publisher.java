package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.ComplexTextElement;
import com.dove.lib.oeb.OEBContract;

/**
 * Created by george on 5/4/14.
 */
public final class Publisher extends ComplexTextElement {

    public Publisher() {
    }

    public Publisher(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.PUBLISHER;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
    }

    public static final ClassLoaderCreator<Publisher> CREATOR = new ClassLoaderCreator<Publisher>() {
        @Override
        public Publisher createFromParcel(Parcel source, ClassLoader loader) {
            return new Publisher(source, loader);
        }

        @Override
        public Publisher createFromParcel(Parcel source) {
            return new Publisher(source, null);
        }

        @Override
        public Publisher[] newArray(int size) {
            return new Publisher[size];
        }
    };
}
