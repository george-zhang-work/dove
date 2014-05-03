package com.dove.reader.provider.book;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Objects;

/**
 * Created by george on 4/26/14.
 */
public class Date implements Parcelable {

    private final Event mEvent;
    private final String mDate;

    public Date(Event event, String date) {
        mEvent = event;
        mDate = date;
    }

    public Date(Parcel in) {
        mEvent = Event.fromValue(in.readString());
        mDate = in.readString();
    }

    public Event getEvent() {
        return mEvent;
    }

    public String getDate() {
        return mDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Date)) {
            return false;
        }
        final Date date = (Date) o;
        if (!Objects.equal(mDate, date.mDate) || mEvent != date.mEvent) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mEvent, mDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mEvent.toString());
        dest.writeString(mDate);
    }

    public static final Creator<Date> CREATOR = new Creator<Date>() {
        @Override
        public Date createFromParcel(Parcel source) {
            return new Date(source);
        }

        @Override
        public Date[] newArray(int size) {
            return new Date[0];
        }
    };
}
