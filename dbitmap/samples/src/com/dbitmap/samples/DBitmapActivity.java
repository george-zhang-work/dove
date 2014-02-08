
package com.dbitmap.samples;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

public class DBitmapActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbitmap);

        ImageView oneView = (ImageView) findViewById(R.id.one_image);
        OneDrawable oneDrawable = new OneDrawable(this, 1);
        oneDrawable.setCount(1);
        oneView.setImageDrawable(oneDrawable);

        ImageView twoView = (ImageView) findViewById(R.id.two_image);
        TwoDrawable twoDrawable = new TwoDrawable(this, 2);
        twoDrawable.setCount(2);
        twoView.setImageDrawable(twoDrawable);

        ImageView threeView = (ImageView) findViewById(R.id.three_image);
        ThreeDrawable threeDrawable = new ThreeDrawable(this, 3);
        threeDrawable.setCount(3);
        threeView.setImageDrawable(threeDrawable);

        ImageView fourView = (ImageView) findViewById(R.id.four_image);
        FourDrawable fourDrawable = new FourDrawable(this, 4);
        fourDrawable.setCount(4);
        fourView.setImageDrawable(fourDrawable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dbitmap, menu);
        return true;
    }

}
