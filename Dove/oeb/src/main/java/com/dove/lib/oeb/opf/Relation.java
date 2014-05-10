package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.ComplexTextElement;
import com.dove.lib.oeb.OEBContract;

/**
 * Created by george on 5/4/14.
 */
public final class Relation extends ComplexTextElement {

    public Relation() {
    }

    public Relation(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.RELATION;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
    }

    public static final ClassLoaderCreator<Relation> CREATOR = new ClassLoaderCreator<Relation>() {
        @Override
        public Relation createFromParcel(Parcel source, ClassLoader loader) {
            return new Relation(source, loader);
        }

        @Override
        public Relation createFromParcel(Parcel source) {
            return new Relation(source, null);
        }

        @Override
        public Relation[] newArray(int size) {
            return new Relation[size];
        }
    };
}
