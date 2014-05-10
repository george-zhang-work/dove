package com.dove.lib.oeb.ncx;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;

/**
 * Created by george on 5/8/14.
 */
public class NavLabel extends DocElement {
    public NavLabel() {
        super();
    }

    public NavLabel(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.NAV_LABEL;
    }

    public static final ClassLoaderCreator<NavLabel> CREATOR = new ClassLoaderCreator<NavLabel>() {
        @Override
        public NavLabel createFromParcel(Parcel source, ClassLoader loader) {
            return new NavLabel(source, loader);
        }

        @Override
        public NavLabel createFromParcel(Parcel source) {
            return new NavLabel(source, null);
        }

        @Override
        public NavLabel[] newArray(int size) {
            return new NavLabel[size];
        }
    };
}
