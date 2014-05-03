package com.dove.reader.provider.book;

/**
 * Created by george on 4/26/14.
 */
public enum Scheme {
    NONE("NONE"),
    UUID("UUID"),
    ISBN("ISBN"),
    URL("URL"),
    URI("URI");

    private final String mValue;

    Scheme(String value) {
        mValue = value;
    }

    public static Scheme formValue(String value) {
        for (Scheme scheme : values()) {
            if (scheme.mValue.equals(value)) {
                return scheme;
            }
        }
        return NONE;
    }

    @Override
    public String toString() {
        return mValue;
    }
}