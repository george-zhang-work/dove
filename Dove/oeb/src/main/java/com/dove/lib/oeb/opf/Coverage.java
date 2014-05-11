package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.ComplexTextElement;
import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.ParcelableCreator;

/**
 * Created by george on 5/4/14.
 */
public final class Coverage extends ComplexTextElement {

    public Coverage() {
    }

    public Coverage(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.COVERAGE;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
    }

    public static final ClassLoaderCreator<Coverage> CREATOR = new ParcelableCreator<>(Coverage.class);
}
