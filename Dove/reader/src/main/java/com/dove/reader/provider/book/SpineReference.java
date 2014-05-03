package com.dove.reader.provider.book;

import android.os.Parcel;

import com.dove.common.log.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by george on 4/26/14.
 */
public class SpineReference extends SimpleReference {

    private final Linear mLinear;
    private final Property mProperty;

    public SpineReference(Resource resource) {
        this("", Linear.YES, null, resource);
    }

    public SpineReference(String id, Linear linear, Property property, Resource resource) {
        super(id, resource);
        mLinear = linear;
        mProperty = property;
    }

    public SpineReference(Parcel in, ClassLoader loader) {
        super(in, loader);
        mLinear = Linear.fromValue(in.readString());
        mProperty = Property.fromValue(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mLinear.toString());
        dest.writeString(mProperty.toString());
    }

    /**
     * Linear denotes whether the section is Primary or Auxiliary.
     * Usually the cover page has linear set to false and all the other sections have it set to true.
     * It's an optional property that readers may also ignore.
     * <blockquote>primary or auxiliary is useful for Reading Systems which opt to present auxiliary content
     * differently than primary content. For example, a Reading System might opt to render auxiliary content
     * in a popup window apart from the main window which presents the primary content.
     * (For an example of the types of content that may be considered auxiliary,
     * refer to the example below and the subsequent discussion.)</blockquote>
     *
     * @return whether the section is Primary or Auxiliary.
     * @see <a href="http://www.idpf.org/epub/20/spec/OPF_2.0.1_draft.htm#Section2.4">OPF Spine specification</a>
     */
    public boolean isLinear() {
        return mLinear == Linear.YES;
    }

    public Property getProperty() {
        return mProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpineReference)) return false;
        if (!super.equals(o)) return false;
        return true;
    }

    @Override
    public String toString() {
        final String reference = super.toString();
        try {
            return new JSONObject(reference)
                .put(OPFContract.OPF.LINEAR, mLinear)
                .put(OPFContract.OPF.PROPERTY, mLinear).toString();
        } catch (JSONException e) {
            LogUtils.wtf(LOG_TAG, "Could not serialize the SpineReference with reference " + reference);
        }
        return "";
    }

    public static final ClassLoaderCreator<SpineReference> CREATOR = new ClassLoaderCreator<SpineReference>() {
        @Override
        public SpineReference createFromParcel(Parcel source, ClassLoader loader) {
            return new SpineReference(source, loader);
        }

        @Override
        public SpineReference createFromParcel(Parcel source) {
            return new SpineReference(source, SpineReference.class.getClassLoader());
        }

        @Override
        public SpineReference[] newArray(int size) {
            return new SpineReference[size];
        }
    };

}
