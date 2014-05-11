package com.dove.lib.oeb;

import android.os.Parcel;
import android.os.Parcelable;

import com.dove.common.log.LogUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by george on 5/11/14.
 */
public class ParcelableCreator<T> implements Parcelable.ClassLoaderCreator<T> {
    private static final String TAG = "ParcelableCreator";
    private final Class<T> mClazz;

    public ParcelableCreator(Class<T> clazz) {
        mClazz = clazz;
    }

    @Override
    public T createFromParcel(Parcel source, ClassLoader loader) {
        try {
            Constructor<T> constructor = mClazz.getConstructor(Parcel.class, ClassLoader.class);
            return constructor.newInstance(source, loader);
        } catch (InstantiationException | IllegalAccessException
            | InvocationTargetException | NoSuchMethodException e) {
            LogUtils.e(TAG, e, "Failed to construct class %s(Parcel in, ClassLoader) as parameters", mClazz);
            return null;
        }
    }

    @Override
    public T createFromParcel(Parcel source) {
        return createFromParcel(source, mClazz.getClassLoader());
    }

    @Override
    public T[] newArray(int size) {
        return (T[]) Array.newInstance(mClazz, size);
    }
}
