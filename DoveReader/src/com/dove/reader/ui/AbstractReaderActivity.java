
package com.dove.reader.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;

import com.dove.reader.ui.interfaces.HelpCallback;
import com.dove.reader.ui.interfaces.RestrictedActivity;

/**
 * <p>
 * A complete Rader activity instance. This is the toplevel class that creates
 * the views and handles the activity lifecycle.
 * </p>
 * <p>
 * This class is abstract to allow many other activities to be quickly created
 * by subclass this activity and overriding a small subset of the life cycle
 * methods.
 * </p>
 */
public abstract class AbstractReaderActivity extends Activity implements HelpCallback,
        RestrictedActivity {
    /**
     * Do not enabled the strict mode for production.
     */
    private static final boolean STRICT_MODE = true;

    private final UiHandler mUiHandler = new UiHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (STRICT_MODE) {
            if (STRICT_MODE) {
                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads()
                        .detectDiskWrites()
                        // or .detectAll() for all detectable problems
                        .detectNetwork().penaltyLog().build());
                StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                        .detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog()
                        .build());
            }
        }

        super.onCreate(savedInstanceState);
        mUiHandler.setEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mUiHandler.setEnabled(true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mUiHandler.setEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mUiHandler.setEnabled(true);
    }

    /**
     * Get the contextual help parameter for this activity. This can be
     * overridden to allow the extending activities to return different help
     * context strings.
     * 
     * @return The help context of this activity.
     */
    @Override
    public String getHelpContext() {
        return "Reader";
    }

    @Override
    public Context getActivityContext() {
        return this;
    }
}
