
package com.dove.reader.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.dove.reader.providers.UIProvider;
import com.dove.reader.ui.interfaces.ControllableActivity;

public abstract class DrawerItem {

    /**
     * The activity that contains the item.
     */
    protected final ControllableActivity mActivity;

    /**
     * The drawer item type defined in {@link UIProvider.FolderType}
     */
    protected final int mItemType;

    public DrawerItem(ControllableActivity activity, int itemType) {
        mActivity = activity;
        mItemType = itemType;
    }

    public final View getView(View convertView, ViewGroup parent) {
        final Context context = mActivity.getActivityContext();
        if (convertView == null) {
            convertView = newView(context, parent);
        }
        bindView(context, convertView);
        return convertView;
    }

    /**
     * Makes a new view to hold the data pointed to by cursor.
     * 
     * @param context Interface to application's global information
     * @param parent The parent to which the new view is attached to
     * @return the newly created view.
     */
    public abstract View newView(Context context, ViewGroup parent);

    /**
     * Bind an existing view to the data pointed to by cursor
     * @param context Interface to application's global information
     * @param view Existing view, returned earlier by newView
     */
    public abstract void bindView(Context context, View view);
}
