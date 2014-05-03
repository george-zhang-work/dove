package com.dove.reader.provider.book;

/**
 * Created by george on 4/26/14.
 */
public enum Event {
    PUBLICATION("publication"),
    MODIFICATION("modification"),
    CREATION("creation");

    private final String mValue;

    Event(String value) {
        mValue = value;
    }

    public static Event fromValue(String value) {
        for (Event event : Event.values()) {
            // TODO Maybe need to check the fuffix.
            if (event.mValue.equals(value)) {
                return event;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return mValue;
    }
}
