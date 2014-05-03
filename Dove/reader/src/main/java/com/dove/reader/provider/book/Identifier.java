package com.dove.reader.provider.book;

import android.os.Parcel;

/**
 * Created by george on 5/2/14.
 */
public class Identifier extends TextElement {

    public Identifier(String id, String text) {
        super(id, text);
    }

    public Identifier(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    public static final ClassLoaderCreator<Identifier> CREATOR = new ClassLoaderCreator<Identifier>() {
        @Override
        public Identifier createFromParcel(Parcel source, ClassLoader loader) {
            return new Identifier(source, loader);
        }

        @Override
        public Identifier createFromParcel(Parcel source) {
            return new Identifier(source, Identifier.class.getClassLoader());
        }

        @Override
        public Identifier[] newArray(int size) {
            return new Identifier[size];
        }
    };
}
