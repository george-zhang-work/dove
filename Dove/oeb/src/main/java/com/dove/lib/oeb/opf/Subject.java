package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.ComplexTextElement;
import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.ParcelableCreator;

/**
 * Created by george on 5/4/14.
 */
public final class Subject extends ComplexTextElement {

    public Subject() {
    }

    public Subject(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.SUBJECT;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
    }

    public static final ClassLoaderCreator<Subject> CREATOR = new ParcelableCreator<>(Subject.class);
}
