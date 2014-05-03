package com.dove.reader.provider.book;

/**
 * Created by george on 5/1/14.
 */
public enum Direction {
    /**
     * When the default value is specified, the Author is expressing no preference and
     * the Reading System may chose the rendering direction. This value must be assumed
     * when the attribute is not specified.
     */
    DEFAULT("default"),
    /**
     * left-to-right
     */
    LTR("ltr"),
    /**
     * right-to-left
     */
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
        return DEFAULT;
    }

    @Override
    public String toString() {
        return mValue;
    }
}
