package com.dove.lib.oeb.ncx;

/**
 * Created by george on 5/9/14.
 */
public enum Type {
    EMPTY(""),
    FRONT("foront"),
    NORMAL("normal"),
    SPECIAL("special");

    private final String mValue;

    Type(String value) {
        mValue = value;
    }

    public static Type fromValue(String value) {
        for (Type type : values()) {
            if (type.mValue.equals(value)) {
                return type;
            }
        }
        return EMPTY;
    }
}
