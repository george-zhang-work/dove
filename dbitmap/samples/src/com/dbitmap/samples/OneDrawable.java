
package com.dbitmap.samples;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;

import com.dove.bitmap.CompositeDrawable;

public class OneDrawable extends CompositeDrawable<BitmapDrawable> {
    private Context mContext;

    public OneDrawable(Context context, int maxDivisions) {
        super(maxDivisions);
        mContext = context;
    }

    @Override
    protected BitmapDrawable createDivisionDrawable(int index) {
        BitmapDrawable dr = (BitmapDrawable) mContext.getResources().getDrawable(
                R.drawable.ic_launcher);
        return dr;
    }

}
