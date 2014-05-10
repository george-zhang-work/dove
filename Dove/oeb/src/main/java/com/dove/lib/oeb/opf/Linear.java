package com.dove.lib.oeb.opf;

/**
 * Created by george on 5/1/14.
 */
public enum Linear {
    YES("yes"), NO("no");

    private final String mValue;

    Linear(String value) {
        mValue = value;
    }

    public static Linear fromValue(String value) {
        for (Linear linear : Linear.values()) {
            if (linear.mValue.equalsIgnoreCase(value)) {
                return linear;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return mValue;
    }
}
