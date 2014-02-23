
package com.dove.reader.ui;

import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.dove.reader.content.ObjectCursor;
import com.dove.reader.log.LogTag;
import com.dove.reader.log.LogUtils;
import com.dove.reader.providers.Folder;
import com.dove.reader.providers.UIProvider.FolderType;
import com.dove.reader.ui.controller.DrawerController;
import com.dove.reader.ui.controller.FolderController;
import com.dove.reader.ui.interfaces.ControllableActivity;

import java.util.ArrayList;

/**
 * A drawer that is shown in one pane mode, as a pull-out from the left.
 */
public class DrawerFragment extends ListFragment implements LoaderCallbacks<ObjectCursor<Folder>> {
    private static final String LOG_TAG = LogTag.getLogTag();

    private static final String ARG_PARENT_FOLDER = "arg-parent-folder";
    private static final String ARG_FOLDER_LIST_URI = "arg-folder-list-uri";
    private static final String ARG_EXCLUDED_FOLDER_TYPES = "arg-exclude-folder-types";

    private static final String BUNDLE_LIST_STATE = "df-list-state";
    private static final String BUNDLE_SELECTED_FOLDER = "df-selected-folder";
    private static final String BUNDLE_SELECTED_TYPE = "df-selected-type";

    /**
     * The parent or the container activityu.
     */
    private ControllableActivity mActivity;

    /**
     * The current folder's parent, or null if it is not a child.
     */
    private Folder mParentFolder;

    /**
     * The content provider URI to return the list of folders. It could be null
     * or {@link Uri#EMPTY}
     */
    private Uri mFoldersUri;

    /**
     * An {@link ArrayList} of {@link FolderType}s to exclude from displaying.
     */
    private ArrayList<Integer> mExcludedFolderTypes;

    /**
     * The currently selected folder (the folder being viewed). This is never
     * null.
     */
    private Uri mSelectedFolderUri = Uri.EMPTY;

    /**
     * Type of currently selected folder. This should be consistent with
     * {@link #mSelectedFolderUri} type.
     */
    private int mSelectedFolderType = FolderType.UNSET;

    /**
     * Observer to be notified when the selected folder changed.
     */
    private DataSetObserver mFolderObserver;

    /**
     * Observer to be notified when the drawer is operated.
     */
    private DataSetObserver mDrawerObserver;

    /**
     * Creates a new instance of {@link DrawerFragment}, initialized to display
     * the folder and its imediate children.
     * 
     * @param folder Parent folder whose children are to be shown.
     * @return A new instance of {@link DrawerFragment}.
     */
    public static DrawerFragment ofTree(Folder folder) {
        final DrawerFragment fragment = new DrawerFragment();
        fragment.setArguments(bundleArguments(folder, folder.getChildFoldersUri(), null));
        return fragment;
    }

    /**
     * Construct a bundle that represents the state of this fragment.
     * 
     * @param parentFolder non-null for trees, the parent of this list
     * @param folderListUri the URI which contains all the list of folders
     * @param excludedFolderTypes indicates folders to exclude in lists.
     * @return Bundle containing parentFolder, divided list boolean and excluded
     *         folder types
     */
    private static Bundle bundleArguments(Folder parentFolder, Uri folderListUri,
            ArrayList<Integer> excludedFolderTypes) {
        final Bundle args = new Bundle(3);
        if (parentFolder != null) {
            args.putParcelable(ARG_PARENT_FOLDER, parentFolder);
        }
        if (folderListUri != null) {
            args.putString(ARG_FOLDER_LIST_URI, folderListUri.toString());
        }
        if (excludedFolderTypes != null) {
            args.putIntegerArrayList(ARG_EXCLUDED_FOLDER_TYPES, excludedFolderTypes);
        }
        return args;
    }

    /**
     * Unbundle the arguments into local variables.
     * 
     * @param args The {@link Bundle} type arguments saving some states.
     */
    private void unbundleArguments(Bundle args) {
        if (args == null) {
            return;
        }
        mParentFolder = args.getParcelable(ARG_PARENT_FOLDER);
        final String foldersUri = args.getString(ARG_FOLDER_LIST_URI);
        if (!TextUtils.isEmpty(foldersUri)) {
            mFoldersUri = Uri.parse(foldersUri);
        } else {
            mFoldersUri = Uri.EMPTY;
        }
        mExcludedFolderTypes = args.getIntegerArrayList(ARG_EXCLUDED_FOLDER_TYPES);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Unbundle arguments to initialize the drawer.
        if (!(activity instanceof ControllableActivity)) {
            LogUtils.wtf(LOG_TAG, "DrawerFragment expects only "
                    + "a ControllableActivity to create it. Cannot proceed.");
            return;
        }
        mActivity = (ControllableActivity) activity;
        unbundleArguments(getArguments());
        CursorAdapter cu;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        return inflater.inflate(android.R.layout.list_content, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedState) {
        super.onViewCreated(view, savedState);
        if (savedState != null) {
            if (savedState.containsKey(BUNDLE_LIST_STATE)) {
                getListView().onRestoreInstanceState(savedState.getParcelable(BUNDLE_LIST_STATE));
            }
            if (savedState.containsKey(BUNDLE_SELECTED_FOLDER)) {
                mSelectedFolderUri = Uri.parse(savedState.getString(BUNDLE_SELECTED_FOLDER));
                mSelectedFolderType = savedState.getInt(BUNDLE_SELECTED_TYPE);
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        final FolderController folderController = mActivity.getFolderController();
        mFolderObserver = new DataSetObserver() {
            @Override
            public void onChanged() {
                onSelectedFolderChanged();
            }
        };
        folderController.getFolderObserverable().registerObserver(mFolderObserver);

        final DrawerController drawerController = mActivity.getDrawerController();
        mDrawerObserver = new DataSetObserver() {
            @Override
            public void onChanged() {
            }
        };
        drawerController.getDrawerObserverable().registerObserver(mDrawerObserver);
    }

    @Override
    public void onDestroyView() {
        if (mDrawerObserver != null) {
            final DrawerController drawerController = mActivity.getDrawerController();
            if (drawerController != null) {
                drawerController.getDrawerObserverable().unregisterObserver(mDrawerObserver);
            }
            mDrawerObserver = null;
        }
        if (mFolderObserver != null) {
            final FolderController folderController = mActivity.getFolderController();
            if (folderController != null) {
                folderController.getFolderObserverable().unregisterObserver(mFolderObserver);
            }
            mFolderObserver = null;
        }

        super.onDestroyView();
    }

    private void onSelectedFolderChanged() {
    }

    @Override
    public Loader<ObjectCursor<Folder>> onCreateLoader(int id, Bundle args) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onLoadFinished(Loader<ObjectCursor<Folder>> loader, ObjectCursor<Folder> data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onLoaderReset(Loader<ObjectCursor<Folder>> loader) {
        // TODO Auto-generated method stub

    }

    static class DrawerAdapter extends CursorAdapter {

        public DrawerAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);
        }

        @SuppressWarnings("unchecked")
        public ObjectCursor<Folder> getObjectCursor() {
            final Cursor cursor = getCursor();
            if (cursor instanceof ObjectCursor<?>) {
                return (ObjectCursor<Folder>) cursor;
            }
            return null;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            // View.inflate(context, resource, root)
            return null;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            // TODO Auto-generated method stub

        }

    }

}
