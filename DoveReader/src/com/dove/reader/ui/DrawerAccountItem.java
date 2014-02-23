
package com.dove.reader.ui;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;

import com.dove.reader.ui.interfaces.ControllableActivity;

public class DrawerAccountItem extends DrawerItem {

    public DrawerAccountItem(ControllableActivity activity, int drawerItemType) {
        super(activity, drawerItemType);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // TODO Auto-generated method stub

    }

}
