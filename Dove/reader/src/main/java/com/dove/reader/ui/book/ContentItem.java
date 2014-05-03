package com.dove.reader.ui.book;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.dove.reader.R;
import com.dove.reader.provider.ReaderContract;
import com.dove.reader.ui.DrawerItem;
import com.dove.reader.ui.DrawerItemView;

/**
 * Created by george on 4/14/14.
 */
public class ContentItem extends DrawerItem {
    private final int mIcon;
    private final String mNumber;
    private final String mText;
    private final String mIndexed;

    public ContentItem(int icon, String number, String text, String indexed) {
        mIcon = icon;
        mNumber = number;
        mText = text;
        mIndexed = indexed;
    }

    @Override
    public int getDrawerItemType() {
        return ReaderContract.DrawerItemTypes.CONTENT;
    }

    @Override
    public DrawerItemView newView(Context context, ViewGroup parent) {
        final View v = View.inflate(context, R.layout.content_item_view, null);
        return (DrawerItemView) v;
    }

    @Override
    public void bindView(Context context, DrawerItemView view) {
        final ContentItemView contentItemView = (ContentItemView) view;
        contentItemView.mIconView.setImageResource(mIcon);
        contentItemView.mNumberView.setText(mNumber);
        contentItemView.mTextView.setText(mText);
        contentItemView.mIndexedView.setText(mIndexed);
    }
}
