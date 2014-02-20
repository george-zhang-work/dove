
package com.dove.reader.ui;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dove.reader.log.LogTag;
import com.dove.reader.ui.interfaces.ControllableActivity;

/**
 * A drawer that is shown in one pane mode, as a pull-out from the left.
 */
public class DrawerFragment extends ListFragment {
    private static final String LOG_TAG = LogTag.getLogTag();

    /** Key to store parent folder. */
    private static final String ARG_PARENT_FOLDER = "arg-parent-folder";

    /** Key to store folder list uri. */
    private static final String ARG_FOLDER_LIST_URI = "arg-folder-list-uri";

    /** Key to store excluded folder types. */
    private static final String ARG_EXCLUDED_FOLDER_TYPES = "arg-exclude-folder-types";

    /**
     * The parent or the container activityu.
     */
    private ControllableActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        return inflater.inflate(android.R.layout.list_content, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedState) {
        super.onViewCreated(view, savedState);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);
    }

}
