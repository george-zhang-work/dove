package com.dove.reader.provider.book;

import android.os.Parcel;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by george on 4/26/14.
 */
public class Guide extends Element {

    private final List<GuideReference> mGuideReferences;

    public Guide() {
        mGuideReferences = Lists.newArrayList();
    }

    public Guide(Parcel in, ClassLoader loader) {
        super(in, loader);
        mGuideReferences = in.readArrayList(loader);
    }

    public List<GuideReference> getGuideReferences() {
        return mGuideReferences;
    }

    public void addGuideReference(GuideReference guideReference) {
        mGuideReferences.add(guideReference);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeList(mGuideReferences);
    }

    public static final ClassLoaderCreator<Guide> CREATOR = new ClassLoaderCreator<Guide>() {
        @Override
        public Guide createFromParcel(Parcel source, ClassLoader loader) {
            return new Guide(source, loader);
        }

        @Override
        public Guide createFromParcel(Parcel source) {
            return new Guide(source, Guide.class.getClassLoader());
        }

        @Override
        public Guide[] newArray(int size) {
            return new Guide[0];
        }
    };

}
