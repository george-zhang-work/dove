
package com.dbitmap.samples;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;

import com.dove.bitmap.CompositeDrawable;

public class TwoDrawable extends CompositeDrawable<BitmapDrawable> {
    private Context mContext;

    public TwoDrawable(Context context, int maxDivisions) {
        super(maxDivisions);
        mContext = context;
    }

    @Override
    protected BitmapDrawable createDivisionDrawable(int index) {
        BitmapDrawable dr = (BitmapDrawable) mContext.getResources().getDrawable(
                index == 0 ? R.drawable.ic_launcher : R.drawable.ic_github);
        return dr;
    }

}
