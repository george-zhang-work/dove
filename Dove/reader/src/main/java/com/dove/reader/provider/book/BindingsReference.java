package com.dove.reader.provider.book;

import android.os.Parcel;

/**
 * Created by george on 5/2/14.
 */
public class BindingsReference extends Reference {

    public BindingsReference(Resource resource) {
        super(resource);
    }

    public BindingsReference(Parcel in, ClassLoader loader) {
        super(in, loader);
    }
}
