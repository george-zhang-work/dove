package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.ComplexTextElement;
import com.dove.lib.oeb.OEBContract;

/**
 * Created by george on 5/4/14.
 */
public final class Subject extends ComplexTextElement {

    public Subject() {
    }

    public Subject(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.SUBJECT;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
    }

    public static final ClassLoaderCreator<Subject> CREATOR = new ClassLoaderCreator<Subject>() {
        @Override
        public Subject createFromParcel(Parcel source, ClassLoader loader) {
            return new Subject(source, loader);
        }

        @Override
        public Subject createFromParcel(Parcel source) {
            return new Subject(source, null);
        }

        @Override
        public Subject[] newArray(int size) {
            return new Subject[size];
        }
    };
}
