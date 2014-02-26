
package com.dove.reader.analytics;

import android.app.Activity;

/**
 * Analytics libraries should implement this interface.
 */
public interface Tracker {

    void activityStart(Activity a);

    void activityStop(Activity a);

    void sendView(String view);

}
