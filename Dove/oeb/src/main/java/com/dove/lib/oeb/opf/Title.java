package com.dove.lib.oeb.opf;

import android.os.Parcel;
import android.os.Parcelable;

import com.dove.lib.oeb.ComplexTextElement;
import com.dove.lib.oeb.OEBContract;

/**
 * Created by george on 5/4/14.
 */
public final class Title extends ComplexTextElement {

    public Title() {
    }

    public Title(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.TITLE;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
    }

    public static final Parcelable.ClassLoaderCreator<Title> CREATOR = new Parcelable.ClassLoaderCreator<Title>() {
        @Override
        public Title createFromParcel(Parcel source, ClassLoader loader) {
            return new Title(source, loader);
        }

        @Override
        public Title createFromParcel(Parcel source) {
            return new Title(source, null);
        }

        @Override
        public Title[] newArray(int size) {
            return new Title[size];
        }
    };
}
