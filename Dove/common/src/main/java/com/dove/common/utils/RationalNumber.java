package com.dove.common.utils;

public final class RationalNumber implements Comparable<RationalNumber> {

    public static RationalNumber ofValue(int numerator, int denominator) {
        if (denominator == 0) {
            return null;
        }
        return new RationalNumber(numerator, denominator);
    }

    private final int mNumerator;
    private final int mDenominator;
    private final int mGcd;

    private RationalNumber(int numerator, int denominator) {
        mNumerator = numerator;
        mDenominator = denominator;
        mGcd = gcd(numerator, denominator);
    }

    public int getNumerator() {
        return mNumerator;
    }

    public int getDenominator() {
        return mDenominator;
    }

    public int getScaledNumberator() {
        return mNumerator / mGcd;
    }

    public int getScaledDenominator() {
        return mDenominator / mGcd;
    }

    public int getGcd() {
        return mGcd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RationalNumber)) {
            return false;
        }
        final RationalNumber other = (RationalNumber) o;
        return getScaledNumberator() == other.getScaledNumberator()
            && getScaledDenominator() == other.getScaledDenominator();
    }

    @Override
    public String toString() {
        return "{" + mNumerator + ", " + mDenominator + ", " + mGcd + "}";
    }

    @Override
    public int hashCode() {
        return 37 * mNumerator + mDenominator;
    }

    @Override
    public int compareTo(RationalNumber o) {
        if (this == o) {
            return 0;
        }
        return getScaledNumberator() * o.getScaledDenominator()
            - o.getScaledNumberator() * getScaledDenominator();
    }

    private static int gcd(int a, int b) {
        if (a < 0) {
            a = -a;
        }
        if (b < 0) {
            b = -b;
        }
        while (a != 0 && b != 0) {
            if (a > b) {
                a %= b;
            } else {
                b %= a;
            }
        }
        return a + b;
    }
}
