package com.dove.common;

import android.content.Context;

public interface ActivityContext {
    /**
     * Returns the context associated with the components(Activity, Dialogs, Fragment, View and so on).
     * This is different from the value returned by {@link android.app.Activity#getApplicationContext()},
     * which is the single context of the root activity. Some components (dialogs) require the context
     * of the activity. When implementing this within an activity, you can return 'this', since each
     * activity is also a context.
     *
     * @return the context associated with this activity.
     */
    Context getActivityContext();
}
