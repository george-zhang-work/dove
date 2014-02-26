
package com.dove.reader.ui;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Handler;

import com.dove.reader.log.LogTag;
import com.dove.reader.log.LogUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A thin wrapper of {@link Handler} to run a callback in UI thread. Any
 * callback to this handler is guarantee to run inside {@link Activity} life
 * cycle. However, it can be dropped if the {@link Activity} has been stopped.
 * This handler is safe to use with {@link FragmentTransaction}.
 */
public class UiHandler {
    private final Handler mHandler = new Handler();
    private boolean mEnabled = true;
    private final static String LOG_TAG = LogTag.getLogTag();

    /** Number of {@link Runnable} in the queue. */
    private AtomicInteger mCount = new AtomicInteger(0);

    /**
     * Causes the Runnable r to be added to the message queue. The runnable will
     * be run on the UI thread to which this handler is attached. If the
     * activity has been stopped, the runnable will be dropped.
     * 
     * @param r The Runnable that will be executed.
     * @return Returns true if the Runnable was successfully placed in to the
     *         message queue. Returns false on failure, usually because the
     *         activity has been stopped.
     * @see Handler#post(Runnable)
     */
    public boolean post(final Runnable r) {
        if (mEnabled) {
            mCount.incrementAndGet();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCount.decrementAndGet();
                    r.run();
                }
            });
        } else {
            LogUtils.d(LOG_TAG, "UiHandler is disabled in post(). Dropping Runnable");
        }
        return mEnabled;
    }

    /**
     * Causes the Runnable r to be added to the message queue. The runnable will
     * be run on the UI thread to which this handler is attached. If the
     * activity has been stopped, the runnable will be dropped.
     * 
     * @param r The Runnable that will be executed.
     * @param delayMillis The delay (in milliseconds) until the Runnable will be
     *            executed.
     * @return Returns true if the Runnable was successfully placed in to the
     *         message queue. Returns false on failure, usually because the
     *         activity has been stopped.
     * @see Handler#postDelayed(Runnable, long)
     */
    public boolean postDelayed(final Runnable r, long delayMillis) {
        if (mEnabled) {
            mCount.incrementAndGet();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mCount.decrementAndGet();
                    r.run();
                }
            }, delayMillis);
        } else {
            LogUtils.d(LOG_TAG, "UiHandler is disabled in post(). Dropping Runnable");
        }
        return mEnabled;
    }

    /**
     * Remove any pending posts of Runnable r that are in the message queue.
     * 
     * @param r The Runnable that will be removed.
     */
    public void removeCallbacks(Runnable r) {
        mHandler.removeCallbacks(r);
    }

    /**
     * To enable the {@link UiHandler} or not. <I> It's safe to edit UI if the
     * {@link UiHandler} is enabled.</I> When set its to be disabled, any
     * pending runnables will be removed.
     * 
     * @param enabled Whether to enabled the {@link UiHandler}.
     */
    public void setEnabled(boolean enabled) {
        mEnabled = enabled;
        if (!mEnabled) {
            int count = mCount.getAndSet(0);
            if (count > 0) {
                LogUtils.e(LOG_TAG, "Disable UiHandler. Dropping  %d Runnables.", count);
            }
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * To check whether the {@link UiHandler} is enabled or not. <I> It's safe
     * to edit UI if the {@link UiHandler} is enabled.</I>
     * 
     * @return True if {@link UiHandler} is enabled, otherwise false.
     */
    public boolean isEnabled() {
        return mEnabled;
    }
}
