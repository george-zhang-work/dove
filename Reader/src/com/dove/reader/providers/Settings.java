
package com.dove.reader.providers;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

/**
 * Holde settings for a Account.
 */
public class Settings implements Parcelable {

    public Settings(Cursor cursor) {
    }

    public Settings(Parcel in, ClassLoader loader) {
    }

    /**
     * Return a serialized String for this settings.
     */
    public synchronized String serialize() {
        return toJson().toString();
    }

    public synchronized JSONObject toJson() {
        final JSONObject json = new JSONObject();
        return json;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static final ClassLoaderCreator<Settings> CREATOR = new ClassLoaderCreator<Settings>() {

        @Override
        public Settings[] newArray(int size) {
            return new Settings[size];
        }

        @Override
        public Settings createFromParcel(Parcel source) {
            return new Settings(source, null);
        }

        @Override
        public Settings createFromParcel(Parcel source, ClassLoader loader) {
            return new Settings(source, loader);
        }
    };
}
