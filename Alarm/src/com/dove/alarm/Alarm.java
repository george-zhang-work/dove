
package com.dove.alarm;

import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.dove.alarm.AlarmContract.AlarmColumns;
import com.dove.alarm.AlarmContract.AlarmType;

public final class Alarm implements Parcelable, AlarmColumns {
    /**
     * The unique ID for a alarm.
     */
    public final long id;

    /**
     * Alarm type defined in {@link AlarmType}
     */
    public final int type;

    /**
     * True if alarm is active.
     */
    public boolean enabled;

    /**
     * Hour in 24-hour localtime 0 - 23.
     */
    public int hour;

    /**
     * Minutes in localtime 0 - 59.
     */
    public int minutes;

    /**
     * Days of week code as a single int.
     * <ul>
     * <li>0x00: no day</li>
     * <li>0x01: Monday</li>
     * <li>0x02: Tuesday</li>
     * <li>0x04: Wednesday</li>
     * <li>0x08: Thursday</li>
     * <li>0x10: Friday</li>
     * <li>0x20: Saturday</li>
     * <li>0x40: Sunday</li>
     * </ul>
     */
    public int days;

    public boolean vibrate;

    public long time;

    public String label;
    public Uri alert;
    public boolean silent;

    public Alarm(Cursor cursor) {
        id = cursor.getLong(cursor.getColumnIndex(AlarmColumns._ID));
        type = cursor.getInt(cursor.getColumnIndex(AlarmColumns.TYPE));
    }

    public Alarm(Parcel in) {
        id = in.readLong();
        type = in.readInt();

        enabled = in.readInt() == 1;
        hour = in.readInt();
        minutes = in.readInt();
        time = in.readLong();
        vibrate = in.readInt() == 1;
        label = in.readString();
        alert = (Uri) in.readParcelable(null);
        silent = in.readInt() == 1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(type);
        dest.writeInt(enabled ? 1 : 0);
        dest.writeInt(hour);
        dest.writeInt(minutes);
        dest.writeLong(time);
        dest.writeInt(vibrate ? 1 : 0);
        dest.writeString(label);
        dest.writeParcelable(alert, flags);
        dest.writeInt(silent ? 1 : 0);
    }

    public static final Parcelable.Creator<Alarm> CREATOR = new Parcelable.Creator<Alarm>() {
        public Alarm createFromParcel(Parcel p) {
            return new Alarm(p);
        }

        public Alarm[] newArray(int size) {
            return new Alarm[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Alarm))
            return false;
        final Alarm other = (Alarm) o;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(id).hashCode();
    }

    @Override
    public String toString() {
        return "Alarm{" + "alert=" + alert + ", id=" + id + ", enabled=" + enabled + ", hour="
                + hour + ", minutes=" + minutes + ", vibrate=" + vibrate + '}';
    }
}
