
package com.dove.reader.analytics;

import android.app.Activity;

/**
 * Reader wrapper for analytics libraries. Libraries should implement
 * {@link Tracker}, and app configurations that want to enable analytics should
 * call {@link #setTracker(Tracker)} as soon as possible upon application start.
 * <p>
 * {@link #getInstance()} will always return an object, but if the app has not
 * yet (or will not ever) set its own tracker instance, method calls on that
 * tracker will be stubbed out.
 */
public final class Analytics {

    private static Tracker sInstance;

    public static Tracker getInstance() {
        synchronized (Analytics.class) {
            if (sInstance == null) {
                // sInstance = new StubTracker();
            }
        }
        return sInstance;
    }

    public static void setTracker(Tracker t) {
        synchronized (Analytics.class) {
            sInstance = t;
        }
    }

    private static final class StubTracker implements Tracker {

        @Override
        public void activityStart(Activity a) {
        }

        @Override
        public void activityStop(Activity a) {
        }

        @Override
        public void sendView(String view) {
        }
    }
}
