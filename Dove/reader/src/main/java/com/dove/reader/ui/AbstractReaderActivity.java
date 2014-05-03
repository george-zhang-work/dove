package com.dove.reader.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;

import com.dove.common.ActivityContext;
import com.dove.common.ApplicationContext;
import com.dove.common.content.UiHandler;

public abstract class AbstractReaderActivity extends Activity implements ApplicationContext, ActivityContext {
    /**
     * Do not enabled the strict mode for production.
     */
    private static final boolean STRICT_MODE = true;

    protected final UiHandler mUiHandler = new UiHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (STRICT_MODE) {
            if (STRICT_MODE) {
                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                        // or .detectAll() for all detectable problems
                    .detectNetwork()
                    .penaltyLog().build());
                StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog().build());
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

    @Override
    public Activity getActivityContext() {
        return this;
    }
}
