package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.ParcelableCreator;
import com.dove.lib.oeb.SimpleTextElement;

public class Identifier extends SimpleTextElement {
    public Identifier() {
        super();
    }

    public Identifier(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    public static final ClassLoaderCreator<Identifier> CREATOR = new ParcelableCreator<>(Identifier.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.IDENTIFIER;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
    }
}
