package com.dove.reader.provider.book;

import android.os.Parcel;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by george on 5/2/14.
 */
public class Bindings implements ParcelableElement {

    private final List<BindingsReference> mBindingsReferences;

    public Bindings() {
        mBindingsReferences = Lists.newArrayList();
    }

    public Bindings(Parcel in, ClassLoader loader) {
        mBindingsReferences = in.readArrayList(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(mBindingsReferences);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final ClassLoaderCreator<Bindings> CREATOR = new ClassLoaderCreator<Bindings>() {
        @Override
        public Bindings createFromParcel(Parcel source, ClassLoader loader) {
            return new Bindings(source, loader);
        }

        @Override
        public Bindings createFromParcel(Parcel source) {
            return new Bindings(source, Bindings.class.getClassLoader());
        }

        @Override
        public Bindings[] newArray(int size) {
            return new Bindings[0];
        }
    };
}
