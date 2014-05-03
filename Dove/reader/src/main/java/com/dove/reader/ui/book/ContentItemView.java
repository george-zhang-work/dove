package com.dove.reader.ui.book;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dove.reader.R;
import com.dove.reader.ui.DrawerItemView;

public class ContentItemView extends LinearLayout implements DrawerItemView {

    /*private*/ ImageView mIconView;
    /*private*/ TextView mNumberView;
    /*private*/ TextView mTextView;
    /*private*/ TextView mIndexedView;

    public ContentItemView(Context context) {
        super(context);
    }

    public ContentItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContentItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mIconView = (ImageView) findViewById(R.id.icon);
        mNumberView = (TextView) findViewById(R.id.number);
        mTextView = (TextView) findViewById(R.id.text);
        mIndexedView = (TextView) findViewById(R.id.indexed);
    }

}
