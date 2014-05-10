package com.dove.lib.oeb.ncx;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;

/**
 * Created by george on 5/8/14.
 */
public class DocTitle extends DocElement {

    public DocTitle() {
        super();
    }

    public DocTitle(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.DOC_TITLE;
    }

    public static final ClassLoaderCreator<DocTitle> CREATOR = new ClassLoaderCreator<DocTitle>() {
        @Override
        public DocTitle createFromParcel(Parcel source, ClassLoader loader) {
            return new DocTitle(source, loader);
        }

        @Override
        public DocTitle createFromParcel(Parcel source) {
            return new DocTitle(source, null);
        }

        @Override
        public DocTitle[] newArray(int size) {
            return new DocTitle[size];
        }
    };
}