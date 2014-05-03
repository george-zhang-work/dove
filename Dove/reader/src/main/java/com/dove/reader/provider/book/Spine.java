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
public class Spine extends Element {

    private Reference mTOCReference;
    private Direction mDirection;
    private final List<SpineReference> mSpineReferences;

    public Spine() {
        this(null, null, Direction.DEFAULT);
    }

    public Spine(String id, Reference TOCReference, Direction direction) {
        super(id);
        mTOCReference = TOCReference;
        mDirection = direction;
        mSpineReferences = Lists.newArrayList();
    }

    public Spine(Parcel in, ClassLoader loader) {
        super(in, loader);
        mTOCReference = in.readParcelable(loader);
        mSpineReferences = in.readArrayList(loader);
        mDirection = Direction.fromValue(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(mTOCReference, flags);
        dest.writeList(mSpineReferences);
        dest.writeString(mDirection.toString());
    }

    public Reference getTOCReference() {
        return mTOCReference;
    }

    public List<SpineReference> getSpineReferences() {
        return mSpineReferences;
    }

    public SpineReference getSpineReference(int index) {
        return mSpineReferences.get(index);
    }

    public int getCount() {
        return mSpineReferences.size();
    }

    public void addSpineReferende(SpineReference reference) {
        mSpineReferences.add(reference);
    }

    @Override
    public String toString() {
        try {
            return new JSONObject(super.toString())
                .put(OPFContract.OPF.TOC, mTOCReference.toString())
                .put(OPFContract.OPF.ITEMS, mSpineReferences)
                .put(OPFContract.OPF.DIRECTION, mDirection.toString())
                .toString();
        } catch (JSONException e) {
            LogUtils.wtf(LOG_TAG, "Could not serialize the Spine with id＝%s, toc=%s, dir=%s",
                mId, mTOCReference, mDirection);
        }
        return "";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final ClassLoaderCreator<Spine> CREATOR = new ClassLoaderCreator<Spine>() {
        @Override
        public Spine createFromParcel(Parcel source, ClassLoader loader) {
            return new Spine(source, loader);
        }

        @Override
        public Spine createFromParcel(Parcel source) {
            return new Spine(source, Spine.class.getClassLoader());
        }

        @Override
        public Spine[] newArray(int size) {
            return new Spine[size];
        }
    };
}
