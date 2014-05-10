package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.ComplexTextElement;
import com.dove.lib.oeb.OEBContract;

/**
 * Created by george on 5/4/14.
 */
public final class Contributor extends ComplexTextElement {

    public Contributor() {
    }

    public Contributor(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.CONTRIBUTOR;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
    }

    public static final ClassLoaderCreator<Contributor> CREATOR = new ClassLoaderCreator<Contributor>() {
        @Override
        public Contributor createFromParcel(Parcel source, ClassLoader loader) {
            return new Contributor(source, loader);
        }

        @Override
        public Contributor createFromParcel(Parcel source) {
            return new Contributor(source, null);
        }

        @Override
        public Contributor[] newArray(int size) {
            return new Contributor[size];
        }
    };
}
