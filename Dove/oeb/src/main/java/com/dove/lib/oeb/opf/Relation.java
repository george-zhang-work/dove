package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.ComplexTextElement;
import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.ParcelableCreator;

/**
 * Created by george on 5/4/14.
 */
public final class Relation extends ComplexTextElement {

    public Relation() {
    }

    public Relation(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    public static final ClassLoaderCreator<Relation> CREATOR = new ParcelableCreator<>(Relation.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.RELATION;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
    }
}
