
package com.dbitmap.samples;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;

import com.dove.bitmap.CompositeDrawable;

public class FourDrawable extends CompositeDrawable<BitmapDrawable> {
    private Context mContext;

    public FourDrawable(Context context, int maxDivisions) {
        super(maxDivisions);
        mContext = context;
    }

    @Override
    protected BitmapDrawable createDivisionDrawable(int index) {
        BitmapDrawable dr = (BitmapDrawable) mContext.getResources().getDrawable(
                index == 0 ? R.drawable.ic_launcher : index == 1 ? R.drawable.ic_github
                        : index == 2 ? R.drawable.ic_beer : R.drawable.ic_photo);
        return dr;
    }

}
