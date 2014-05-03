package com.dove.reader.provider.book;

import android.os.Parcel;

import com.dove.common.log.LogUtils;
import com.google.common.base.Objects;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by george on 5/2/14.
 */
public class SimpleTextElement extends TextElement {

    protected final String mXmlLang;
    protected final Direction mDir;

    public SimpleTextElement(String id, String text) {
        this(id, "", Direction.DEFAULT, text);
    }

    public SimpleTextElement(String id, String xmlLang, Direction dir, String text) {
        super(id, text);
        mXmlLang = xmlLang;
        mDir = dir;
    }

    public SimpleTextElement(Parcel in, ClassLoader loader) {
        super(in, loader);
        mXmlLang = in.readString();
        mDir = Direction.fromValue(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mXmlLang);
        dest.writeString(mDir.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleTextElement)) return false;
        if (!super.equals(o)) return false;

        final SimpleTextElement title = (SimpleTextElement) o;
        return Objects.equal(mDir, title.mDir)
            && Objects.equal(mXmlLang, title.mXmlLang);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), mDir, mText, mXmlLang);
    }

    @Override
    public String toString() {
        try {
            return new JSONObject(super.toString())
                .put(OPFContract.OPF.DIRECTORY, mDir)
                .put(OPFContract.OPF.XML_LANG, mXmlLang)
                .toString();
        } catch (JSONException e) {
            LogUtils.wtf(LOG_TAG, "Could not serialize the SimpleTextElement with info" +
                " {id=%s, text=%s, xml:lang=%s, dir=%s", mId, mText, mXmlLang, mDir);
        }
        return "";
    }

    public static final ClassLoaderCreator<SimpleTextElement> CREATOR = new ClassLoaderCreator<SimpleTextElement>() {
        @Override
        public SimpleTextElement createFromParcel(Parcel source, ClassLoader loader) {
            return new SimpleTextElement(source, loader);
        }

        @Override
        public SimpleTextElement createFromParcel(Parcel source) {
            return new SimpleTextElement(source, SimpleTextElement.class.getClassLoader());
        }

        @Override
        public SimpleTextElement[] newArray(int size) {
            return new SimpleTextElement[size];
        }
    };
}
