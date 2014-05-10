package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.SimpleElement;

/**
 * Created by george on 5/6/14.
 */
public class Language extends SimpleElement {

    public Language() {
        super();
    }

    public Language(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.LANGUAGE;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
    }

    public static final ClassLoaderCreator<Language> CREATOR = new ClassLoaderCreator<Language>() {
        @Override
        public Language createFromParcel(Parcel source, ClassLoader loader) {
            return new Language(source, loader);
        }

        @Override
        public Language createFromParcel(Parcel source) {
            return new Language(source, null);
        }

        @Override
        public Language[] newArray(int size) {
            return new Language[size];
        }
    };
}
