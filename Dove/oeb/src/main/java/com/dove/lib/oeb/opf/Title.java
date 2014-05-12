package com.dove.lib.oeb.opf;

import android.os.Parcel;
import android.os.Parcelable;

import com.dove.lib.oeb.ComplexTextElement;
import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.ParcelableCreator;

/**
 * Created by george on 5/4/14.
 */
public class Title extends ComplexTextElement {

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

    public static final Parcelable.ClassLoaderCreator<Title> CREATOR = new ParcelableCreator<>(Title.class);
}
