/**
 * 
 */

package com.dove.reader.ui;

import android.os.Bundle;

import com.dove.reader.analytics.Analytics;
import com.dove.reader.log.LogUtils;
import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * Represents the view mode for the tablet Rader activity. Transitions between
 * modes should be done through this central object, and UI components that are
 * dependent on the mode should listen to changes on this objects.
 */
public class ViewMode {
    private static final String LOG_TAG = "ViewMode";

    /**
     * Keywords used to save this ViewMode.
     */
    private static final String VIEW_MODE_KEY = "view-mode";

    /**
     * Uncertain mode. The mode has not been initialized.
     */
    public static final int UNKNOWN = 0;

    /**
     * Friendly names (not user-facing) for each view mode, indexed by ordinal
     * value.
     */
    private static final String[] MODE_NAMES = {
        "Unkown"
    };

    /**
     * A listener for changes on a ViewMode. To listen to mode changes,
     * implement this interface and register your object with the single
     * ViewMode held by the ActivityController instance. On mode changes, teh
     * onViewChanged method will be called with the new mode.
     */
    public interface ModeChangeListener {
        /**
         * Called when the mode has changed.
         * 
         * @param newMode The new view mode.
         */
        void onViewModeChanged(int newMode);
    }

    private final ArrayList<ModeChangeListener> mListeners = Lists.newArrayList();

    /**
     * The actual mode the activity is in. Start out with an UNKOWN mode, and
     * require entering a valide mode after the object has been created.
     */
    private int mMode = 0;

    public ViewMode() {
        // Do nothing
    }

    @Override
    public String toString() {
        return "[mode=" + MODE_NAMES[mMode] + "]";
    }

    /**
     * Get the the view mode represented by human readeable string.
     * 
     * @return The view mode string.
     */
    public String getModeString() {
        return MODE_NAMES[mMode];
    }

    /**
     * Get the current view mode.
     * 
     * @return view mode.
     */
    public int getMode() {
        return mMode;
    }

    /**
     * Add a listener from this view mode.
     * 
     * @param listener A {@link ModeChangeListener}.
     */
    public void addListener(ModeChangeListener listener) {
        mListeners.add(listener);
    }

    /**
     * Remove a listener from this view mode. Must happen in the UI thread.
     * 
     * @param listener A {@link ModeChangeListener}.
     */
    public void removeListener(ModeChangeListener listener) {
        mListeners.remove(listener);
    }

    /**
     * Restoring from a saved state restores only the mode. It does not restore
     * the listeners of this object.
     * 
     * @param inState The input saved state.
     */
    public void handleRestore(Bundle inState) {
        if (inState != null) {
            // Restore the previous mode, and UNKONWN if nothing exists.
            final int newMode = inState.getInt(VIEW_MODE_KEY, UNKNOWN);
            if (newMode != UNKNOWN) {
                setModeInternal(newMode);
            }
        }
    }

    public void handleSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putInt(VIEW_MODE_KEY, mMode);
        }
    }

    /**
     * Sets the internal mode.
     * 
     * @param mode The next view mode.
     * @return Whether or not a change occurred.
     */
    private boolean setModeInternal(int mode) {
        if (mMode == mode) {
            if (LogUtils.isLoggable(LOG_TAG, LogUtils.DEBUG)) {
                LogUtils.d(LOG_TAG, new Error(), "ViewMode: debouncing change attempt mode=%s",
                        mode);
            } else {
                LogUtils.i(LOG_TAG, "ViewMode: debouncing change attempt mode=%s", mode);
            }
            return false;
        }

        if (LogUtils.isLoggable(LOG_TAG, LogUtils.DEBUG)) {
            LogUtils.d(LOG_TAG, new Error(), "ViewMode: executing change old=%s new=%s", mMode,
                    mode);
        } else {
            LogUtils.i(LOG_TAG, "ViewMode: executing change old=%s new=%s", mMode, mode);
        }

        mMode = mode;
        dispatchModeChange();
        Analytics.getInstance().sendView("ViewMode" + toString());
        return true;
    }

    /**
     * Dispatches a change event for the mode. Always happens in the UI thread.
     */
    private void dispatchModeChange() {
        ArrayList<ModeChangeListener> list = new ArrayList<ModeChangeListener>(mListeners);
        for (ModeChangeListener listener : list) {
            assert (listener != null);
            listener.onViewModeChanged(mMode);
        }
    }
}
