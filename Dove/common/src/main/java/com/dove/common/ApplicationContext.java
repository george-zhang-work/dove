package com.dove.common;

import android.content.Context;

public interface ApplicationContext {
    /**
     * Return the context of the single, global Application object of the
     * current process.  This generally should only be used if you need a
     * Context whose lifecycle is separate from the current context, that is
     * tied to the lifetime of the process rather than the current component.
     *
     * @see android.app.Activity#getApplicationContext()
     */
    Context getApplicationContext();
}
