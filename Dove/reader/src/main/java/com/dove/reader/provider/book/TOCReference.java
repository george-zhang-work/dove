package com.dove.reader.provider.book;

import android.os.Parcel;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by george on 4/26/14.
 */
public class TOCReference extends Reference {

    private final int mOrder;
    private final String mLabel;
    private final String mFragmentId;
    private final List<TOCReference> mChildReferences;

    public TOCReference(Resource resource, int order, String label, String fragmentId) {
        super(resource);
        mOrder = order;
        mLabel = label;
        mFragmentId = fragmentId;
        mChildReferences = Lists.newArrayList();
    }

    public TOCReference(Parcel in, ClassLoader loader) {
        super(in, loader);
        mOrder = in.readInt();
        mLabel = in.readString();
        mFragmentId = in.readString();
        mChildReferences = in.readArrayList(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(mOrder);
        dest.writeString(mLabel);
        dest.writeString(mFragmentId);
        dest.writeList(mChildReferences);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TOCReference)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final TOCReference that = (TOCReference) o;
        if (!Objects.equal(mChildReferences, that.mChildReferences)
            && Objects.equal(mFragmentId, that.mFragmentId)) {
            return false;
        }
        return true;
    }

    public static final ClassLoaderCreator<TOCReference> CREATOR = new ClassLoaderCreator<TOCReference>() {
        @Override
        public TOCReference createFromParcel(Parcel source, ClassLoader loader) {
            return new TOCReference(source, loader);
        }

        @Override
        public TOCReference createFromParcel(Parcel source) {
            return new TOCReference(source, TOCReference.class.getClassLoader());
        }

        @Override
        public TOCReference[] newArray(int size) {
            return new TOCReference[size];
        }
    };
}
