package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.ComplexTextElement;
import com.dove.lib.oeb.OEBContract;

/**
 * Created by george on 5/4/14.
 */
public final class Author extends ComplexTextElement {

    public Author() {
    }

    public Author(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.CREATOR;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
    }

    public static final ClassLoaderCreator<Author> CREATOR = new ClassLoaderCreator<Author>() {
        @Override
        public Author createFromParcel(Parcel source, ClassLoader loader) {
            return new Author(source, loader);
        }

        @Override
        public Author createFromParcel(Parcel source) {
            return new Author(source, null);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };
}
