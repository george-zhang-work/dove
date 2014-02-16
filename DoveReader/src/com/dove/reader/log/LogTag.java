
package com.dove.reader.log;

public class LogTag {
    private static String sLogTag = "DoveReader";

    /**
     * Get the log tag to apply to logging.
     */
    public static String getLogTag() {
        return sLogTag;
    }

    /**
     * Sets the app-wide log tag to be used in most log messages, and for
     * enabling logging verbosity. This should be called at most once, during
     * app start-up.
     */
    public static void setLogTag(final String logTag) {
        sLogTag = logTag;
    }
}
