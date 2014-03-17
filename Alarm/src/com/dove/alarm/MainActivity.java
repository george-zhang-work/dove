
package com.dove.alarm;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.AsyncTaskLoader;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

public class MainActivity extends Activity implements LoaderCallbacks<Alarm> {

    private TimePicker mFirstPicker;
    private TimePicker mSecondPicker;
    private TimePicker mThirdPicker;
    private TimePicker mOtherPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirstPicker = (TimePicker) findViewById(R.id.first_timer_picker);
        mSecondPicker = (TimePicker) findViewById(R.id.second_time_picker);
        mThirdPicker = (TimePicker) findViewById(R.id.third_time_picker);
        mOtherPicker = (TimePicker) findViewById(R.id.other_time_picker);

        mFirstPicker.setIs24HourView(true);
        mSecondPicker.setIs24HourView(true);
        mThirdPicker.setIs24HourView(true);
        mOtherPicker.setIs24HourView(true);

        View.OnClickListener timeSetListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            }
        };

        findViewById(R.id.first_set).setOnClickListener(timeSetListener);
        findViewById(R.id.second_set).setOnClickListener(timeSetListener);
        findViewById(R.id.third_set).setOnClickListener(timeSetListener);
        findViewById(R.id.other_set).setOnClickListener(timeSetListener);

        // Try to read the time set from db.
        // getLoaderManager().initLoader(id, args, this);
    }

    @Override
    public Loader<Alarm> onCreateLoader(int id, Bundle args) {
        return new AlarmLoader(MainActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<Alarm> loader, Alarm data) {
    }

    @Override
    public void onLoaderReset(Loader<Alarm> loader) {
    }

    class AlarmLoader extends AsyncTaskLoader<Alarm> {

        public AlarmLoader(Context context) {
            super(context);
        }

        @Override
        public Alarm loadInBackground() {
            ContentResolver resolover = getContentResolver();

            return null;
        }
    }
}
