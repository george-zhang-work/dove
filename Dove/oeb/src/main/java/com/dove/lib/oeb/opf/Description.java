package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.ComplexTextElement;
import com.dove.lib.oeb.OEBContract;

/**
 * Created by george on 5/4/14.
 */
public final class Description extends ComplexTextElement {

    public Description() {
    }

    public Description(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.DESCRIPTION;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
    }

    public static final ClassLoaderCreator<Description> CREATOR = new ClassLoaderCreator<Description>() {
        @Override
        public Description createFromParcel(Parcel source, ClassLoader loader) {
            return new Description(source, loader);
        }

        @Override
        public Description createFromParcel(Parcel source) {
            return new Description(source, null);
        }

        @Override
        public Description[] newArray(int size) {
            return new Description[size];
        }
    };
}
