package com.dove.reader.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * This class defined all the opened interface that's bind to {@link com.dove.reader.ui.DrawerFragment.DrawerAdapter}.
 *
 */
public abstract class DrawerItem {

    /**
     * Get this DrawerItem's type integer value, it's one of the {@link com.dove.reader.provider.ReaderContract.DrawerItemTypes}
     * defined value.
     *
     * @return This drawer item's type.
     */
    public abstract int getDrawerItemType();

    /**
     * For a specific drawer item, get a drawer item view that displays it. The drawer item view comes from
     * previous inflated view or a new view. For better performance, reuse previous inflated way is fine.
     *
     * @param position    The position of the item within the adapter's data set.
     * @param convertView The old view to reuse, if possible.
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    public final View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        DrawerItemView drawerItemView = (DrawerItemView) convertView;
        if (drawerItemView == null) {
            drawerItemView = newView(context, parent);
        }
        bindView(context, drawerItemView);
        return convertView;
    }

    /**
     * Makes a new DrawerItemView to hold this specified type's DrawerItem.
     *
     * @param context The Context that's used to inflated the drawer item view.
     *                Optional, we can use parent.getContext() instead.
     * @param parent  The parent to which the new drawer item view is attached to
     * @return the newly created DrawerItemView.
     */
    public abstract DrawerItemView newView(Context context, ViewGroup parent);

    /**
     * Bind an existing DrawerItemView to this specified type's DrawerItem.
     *
     * @param context The Context that's contains the DrawerItemView.
     * @param view    Existing DrawerItemView, used to display this DrawerItem info.
     */
    public abstract void bindView(Context context, DrawerItemView view);
}
