package com.dove.lib.oeb.ncx;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;

/**
 * Created by george on 5/8/14.
 */
public class NavInfo extends DocElement {
    public NavInfo() {
        super();
    }

    public NavInfo(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.NAV_INFO;
    }

    public static final ClassLoaderCreator<NavInfo> CREATOR = new ClassLoaderCreator<NavInfo>() {
        @Override
        public NavInfo createFromParcel(Parcel source, ClassLoader loader) {
            return new NavInfo(source, loader);
        }

        @Override
        public NavInfo createFromParcel(Parcel source) {
            return new NavInfo(source, null);
        }

        @Override
        public NavInfo[] newArray(int size) {
            return new NavInfo[size];
        }
    };
}
