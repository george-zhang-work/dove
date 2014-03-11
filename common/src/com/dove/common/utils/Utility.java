
package com.dove.common.utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utility {
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final Charset ASCII = Charset.forName("US-ASCII");

    /**
     * Convert a String object to UTF-8 bytes array.
     * 
     * @param s The String to be converted.
     * @return UTF-8 encoded bytes array.
     */
    public static byte[] toUtf8(String s) {
        return encode(UTF_8, s);
    }

    /**
     * Build a String from UTF-8 bytes array.
     * 
     * @param b The bytes array to be decoded.
     * @return The byte relative String object.
     */
    public static String fromUtf8(byte[] b) {
        return decode(UTF_8, b);
    }

    /**
     * Convert a String object to ASCII bytes array.
     * 
     * @param s The String to be converted.
     * @return ASCII encoded bytes array.
     */
    public static byte[] toAscii(String s) {
        return encode(ASCII, s);
    }

    /**
     * Build a String from ASCII bytes array.
     * 
     * @param b The bytes array to be decoded.
     * @return The byte relative String object.
     */
    public static String fromAscii(byte[] b) {
        return decode(ASCII, b);
    }

    private static byte[] encode(Charset charset, String s) {
        if (s == null) {
            return null;
        }
        final ByteBuffer buffer = charset.encode(CharBuffer.wrap(s));
        final byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        return bytes;
    }

    private static String decode(Charset charset, byte[] b) {
        if (b == null) {
            return null;
        }
        final CharBuffer buffer = charset.decode(ByteBuffer.wrap(b));
        return new String(buffer.array(), 0, buffer.length());
    }

    public static String getSmallHash(final String value) {
        final MessageDigest sha;
        try {
            sha = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        sha.update(Utility.toUtf8(value));
        final int hash = getSmallHashFromSha1(sha.digest());
        return Integer.toString(hash);
    }

    private static int getSmallHashFromSha1(byte[] sha1) {
        final int offset = sha1[19] & 0xf;
        return ((sha1[offset] & 0x7f) << 24)
                | ((sha1[offset + 1] & 0xff) << 16)
                | ((sha1[offset + 2] & 0xff) << 8) | ((sha1[offset + 3] & 0xff));
    }
}
