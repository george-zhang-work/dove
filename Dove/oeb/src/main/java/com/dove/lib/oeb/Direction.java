package com.dove.lib.oeb;

import com.google.gson.annotations.SerializedName;

/**
 * Created by george on 5/1/14.
 */
public enum Direction {
    @SerializedName("")
    EMPTY(""),
    /**
     * When the default value is specified, the Author is expressing no preference and
     * the Reading System may chose the rendering direction. This value must be assumed
     * when the attribute is not specified.
     */
    @SerializedName("default")
    DEFAULT("default"),
    /**
     * left-to-right
     */
    @SerializedName("ltr")
    LTR("ltr"),
    /**
     * right-to-left
     */
    @SerializedName("rtl")
    RTL("rtl");

    private final String mValue;

    Direction(String value) {
        mValue = value;
    }

    public static Direction fromValue(String value) {
        for (Direction dir : values()) {
            if (dir.mValue.equals(value)) {
                return dir;
            }
        }
        return EMPTY;
    }

    @Override
    public String toString() {
        return mValue;
    }
}
