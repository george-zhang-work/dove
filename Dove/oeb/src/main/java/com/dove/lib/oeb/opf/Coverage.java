package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.ComplexTextElement;
import com.dove.lib.oeb.OEBContract;

/**
 * Created by george on 5/4/14.
 */
public final class Coverage extends ComplexTextElement {

    public Coverage() {
    }

    public Coverage(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.COVERAGE;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
    }

    public static final ClassLoaderCreator<Coverage> CREATOR = new ClassLoaderCreator<Coverage>() {
        @Override
        public Coverage createFromParcel(Parcel source, ClassLoader loader) {
            return new Coverage(source, loader);
        }

        @Override
        public Coverage createFromParcel(Parcel source) {
            return new Coverage(source, null);
        }

        @Override
        public Coverage[] newArray(int size) {
            return new Coverage[size];
        }
    };
}
