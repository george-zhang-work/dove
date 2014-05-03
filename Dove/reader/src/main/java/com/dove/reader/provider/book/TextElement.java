package com.dove.reader.provider.book;

import android.os.Parcel;

import com.dove.common.log.LogUtils;
import com.google.common.base.Objects;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by george on 4/26/14.
 */
public class TextElement extends Element {
    protected final String mText;

    public TextElement(String id, String text) {
        super(id);
        mText = text;
    }

    public TextElement(Parcel in, ClassLoader loader) {
        super(in, loader);
        mText = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mText);
    }

    public String getText() {
        return mText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextElement)) return false;
        if (!super.equals(o)) return false;

        final TextElement that = (TextElement) o;
        return Objects.equal(mText, that.mText);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), mText);
    }

    @Override
    public String toString() {
        try {
            return new JSONObject(super.toString())
                .put(OPFContract.OPF.TEXT, mText).toString();
        } catch (JSONException e1) {
            LogUtils.wtf(LOG_TAG, "Could not serialize the TextElement with id %s and text %s", mId, mText);
        }
        return "";
    }

    public static final ClassLoaderCreator<TextElement> CREATOR = new ClassLoaderCreator<TextElement>() {
        @Override
        public TextElement createFromParcel(Parcel source, ClassLoader loader) {
            return new TextElement(source, loader);
        }

        @Override
        public TextElement createFromParcel(Parcel source) {
            return new TextElement(source, TextElement.class.getClassLoader());
        }

        @Override
        public TextElement[] newArray(int size) {
            return new TextElement[size];
        }
    };
}
