package com.dove.reader.provider.book;

import android.os.Parcel;

import com.dove.common.log.LogUtils;
import com.google.common.collect.Lists;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by george on 4/26/14.
 */
public class Manifest extends Element {

    private final List<Resource> mItems;

    public Manifest() {
        mItems = Lists.newArrayList();
    }

    public Manifest(Parcel in, ClassLoader loader) {
        super(in, loader);
        mItems = in.readArrayList(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeList(mItems);
    }

    public List<Resource> getItems() {
        return mItems;
    }

    public void addItem(Resource resource) {
        mItems.add(resource);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        try {
            return new JSONObject(super.toString())
                .put(OPFContract.OPF.ITEMS, mItems)
                .toString();
        } catch (JSONException e) {
            LogUtils.wtf(LOG_TAG, "Could not serialize the Manifest with id=%s, items=%s", mId, mItems);
        }
        return "";
    }

    public static final ClassLoaderCreator<Manifest> CREATOR = new ClassLoaderCreator<Manifest>() {
        @Override
        public Manifest createFromParcel(Parcel source, ClassLoader loader) {
            return new Manifest(source, loader);
        }

        @Override
        public Manifest createFromParcel(Parcel source) {
            return new Manifest(source, Manifest.class.getClassLoader());
        }

        @Override
        public Manifest[] newArray(int size) {
            return new Manifest[size];
        }
    };
}
