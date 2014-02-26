
package com.dove.reader.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.dove.reader.R;
import com.dove.reader.ui.interfaces.ControllableActivity;

public class DrawerAccountItem extends DrawerItem {

    public DrawerAccountItem(ControllableActivity activity, int itemType) {
        super(activity, itemType);
    }

    @Override
    public View newView(Context context, ViewGroup parent) {
        return View.inflate(context, R.layout.account_item, null);
    }

    @Override
    public void bindView(Context context, View view) {
        AccountItemView itemView = (AccountItemView) view;
        itemView.bind(null, true, 0);
    }
}
