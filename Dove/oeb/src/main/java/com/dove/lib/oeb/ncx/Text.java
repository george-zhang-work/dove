package com.dove.lib.oeb.ncx;

import android.os.Parcel;

import com.dove.lib.oeb.OEBContract;
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

    public static final ClassLoaderCreator<Text> CREATOR = new ClassLoaderCreator<Text>() {
        @Override
        public Text createFromParcel(Parcel source, ClassLoader loader) {
            return new Text(source, loader);
        }

        @Override
        public Text createFromParcel(Parcel source) {
            return new Text(source, null);
        }

        @Override
        public Text[] newArray(int size) {
            return new Text[size];
        }
    };
}
