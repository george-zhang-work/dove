package com.dove.lib.oeb.ncx;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;

/**
 * Created by george on 5/8/14.
 */
public class DocAuthor extends DocElement {

    public DocAuthor() {
        super();
    }

    public DocAuthor(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.DOC_AUTHOR;
    }

    public static final ClassLoaderCreator<DocAuthor> CREATOR = new ClassLoaderCreator<DocAuthor>() {
        @Override
        public DocAuthor createFromParcel(Parcel source, ClassLoader loader) {
            return new DocAuthor(source, loader);
        }

        @Override
        public DocAuthor createFromParcel(Parcel source) {
            return new DocAuthor(source, null);
        }

        @Override
        public DocAuthor[] newArray(int size) {
            return new DocAuthor[size];
        }
    };
}