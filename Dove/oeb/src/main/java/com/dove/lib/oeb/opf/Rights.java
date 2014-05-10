package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.ComplexTextElement;
import com.dove.lib.oeb.OEBContract;

/**
 * Created by george on 5/4/14.
 */
public final class Rights extends ComplexTextElement {

    public Rights() {
    }

    public Rights(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.RIGHTS;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
    }

    public static final ClassLoaderCreator<Rights> CREATOR = new ClassLoaderCreator<Rights>() {
        @Override
        public Rights createFromParcel(Parcel source, ClassLoader loader) {
            return new Rights(source, loader);
        }

        @Override
        public Rights createFromParcel(Parcel source) {
            return new Rights(source, null);
        }

        @Override
        public Rights[] newArray(int size) {
            return new Rights[size];
        }
    };
}
