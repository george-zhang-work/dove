package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.ComplexTextElement;
import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.ParcelableCreator;

/**
 * Created by george on 5/4/14.
 */
public final class Description extends ComplexTextElement {

    public Description() {
    }

    public Description(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.DESCRIPTION;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
    }

    public static final ClassLoaderCreator<Description> CREATOR = new ParcelableCreator<>(Description.class);
}
