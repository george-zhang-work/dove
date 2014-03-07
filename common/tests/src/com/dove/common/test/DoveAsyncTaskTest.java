
package com.dove.common.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.dove.common.content.DoveAsyncTask;

public class DoveAsyncTaskTest extends AndroidTestCase {
    private static final String TAG = "DoveAsyncTaskTest";
    private static final long THREAD_SLEEP_TIME_MILLIS = 60 * 1000;

    public DoveAsyncTaskTest() {
    }

    final class TestAsyncTask extends DoveAsyncTask<String, Integer, String> {

        public TestAsyncTask(DoveAsyncTask.Tracker tracker) {
            super(tracker);
        }

        @Override
        protected String doInBackground(String... params) {
            if (params != null && params.length > 0) {
                Log.d(TAG, "doInBackground " + params.toString());
            }
            try {
                Thread.sleep(THREAD_SLEEP_TIME_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Return form doInBackground";
        }
    }

    public void testParallel() {
        DoveAsyncTask.Tracker tracker = new DoveAsyncTask.Tracker();
        for (int i = 0; i < 100; i++) {
            new TestAsyncTask(tracker).executeParallel("task " + i);
        }
        tracker.interrupt();
    }
}
