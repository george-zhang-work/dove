package com.dove.lib.oeb.ncx;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.ParcelableCreator;
import com.dove.lib.oeb.SimpleTextElement;

/**
 * Created by george on 5/8/14.
 */
public class Text extends SimpleTextElement {
    public Text() {
        super();
    }

    public Text(Parcel in, ClassLoader loader) {
        super(in, loader);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.TEXT;
    }

    public static final ClassLoaderCreator<Text> CREATOR = new ParcelableCreator<>(Text.class);
}
