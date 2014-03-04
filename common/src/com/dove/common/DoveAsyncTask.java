
package com.dove.common;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public abstract class DoveAsyncTask<Params, Progress, Result> {
    /**
     * All the task in this Executors are executed serialily.
     */
    private static final Executor SERIAL_EXECUTOR = AsyncTask.SERIAL_EXECUTOR;

    /**
     * All the task in this Executors are executed parallelly.
     */
    private static final Executor PARALLEL_EXECUTOR = AsyncTask.THREAD_POOL_EXECUTOR;

    public static class Tracker {
        private final LinkedList<DoveAsyncTask<?, ?, ?>> mTasks = new LinkedList<DoveAsyncTask<?, ?, ?>>();

        private void add(DoveAsyncTask<?, ?, ?> task) {
            synchronized (mTasks) {
                mTasks.add(task);
            }
        }

        private void remove(DoveAsyncTask<?, ?, ?> task) {
            synchronized (mTasks) {
                mTasks.remove(task);
            }
        }

        public void cancelAllInterrupt() {
            synchronized (mTasks) {
                for (DoveAsyncTask<?, ?, ?> task : mTasks) {
                    task.cancel(true);
                }
                mTasks.clear();
            }
        }

        void cancelOthers(DoveAsyncTask<?, ?, ?> current) {
            final Class<?> clazz = current.getClass();
            synchronized (mTasks) {
                for (DoveAsyncTask<?, ?, ?> task : mTasks) {
                    if ((task != current) && task.getClass().equals(clazz)) {
                        task.cancel(true);
                    }
                }
                mTasks.clear();
                mTasks.add(current);
            }
        }
    }

    private final Tracker mTracker;

    static class InnerTask<Params2, Progress2, Result2> extends
            AsyncTask<Params2, Progress2, Result2> {
        private final DoveAsyncTask<Params2, Progress2, Result2> mOwner;

        public InnerTask(DoveAsyncTask<Params2, Progress2, Result2> owner) {
            mOwner = owner;
        }

        @Override
        protected void onPreExecute() {
            mOwner.onPreExecute();
        }

        @Override
        protected Result2 doInBackground(Params2... params) {
            return mOwner.doInBackground(params);
        }

        @Override
        protected void onCancelled(Result2 result) {
            mOwner.unregisterSelf();
            mOwner.onCancelled(result);
        }

        @Override
        protected void onPostExecute(Result2 result) {
            mOwner.unregisterSelf();
            if (mOwner.mCancelled) {
                mOwner.onCancelled(result);
            } else {
                mOwner.onSuccess(result);
            }
        }

        @Override
        protected void onProgressUpdate(Progress2... values) {
            mOwner.onProgressUpdate(values);
        }
    }

    private final InnerTask<Params, Progress, Result> mInnerTask;

    private volatile boolean mCancelled;

    public DoveAsyncTask(Tracker tracker) {
        mTracker = tracker;
        if (mTracker != null) {
            mTracker.add(this);
        }
        mInnerTask = new InnerTask<Params, Progress, Result>(this);
    }

    final void unregisterSelf() {
        if (mTracker != null) {
            mTracker.remove(this);
        }
    }

    protected abstract Result doInBackground(Params... params);

    public final void cancel(boolean mayInterruptIfRunning) {
        mCancelled = true;
        mInnerTask.cancel(mayInterruptIfRunning);
    }

    protected void onPreExecute() {
    }

    protected void onSuccess(Result result) {
    }

    protected void onCancelled(Result result) {
        onCancelled();
    }

    protected void onCancelled() {
    }

    protected void onProgressUpdate(Progress... values) {
    }

    public final Result get() throws InterruptedException, ExecutionException {
        return mInnerTask.get();
    }

    public final DoveAsyncTask<Params, Progress, Result> executeParallel(Params... params) {
        return executeInternal(PARALLEL_EXECUTOR, false, params);
    }

    public final DoveAsyncTask<Params, Progress, Result> cancelPreviousAndExecuteParallel(
            Params... params) {
        return executeInternal(PARALLEL_EXECUTOR, true, params);
    }

    public final DoveAsyncTask<Params, Progress, Result> executeSerial(Params... params) {
        return executeInternal(SERIAL_EXECUTOR, false, params);
    }

    public final DoveAsyncTask<Params, Progress, Result> cancelPreviousAndExecuteSerial(
            Params... params) {
        return executeInternal(SERIAL_EXECUTOR, true, params);
    }

    private DoveAsyncTask<Params, Progress, Result> executeInternal(Executor executor,
            boolean cancelPrevious, Params... params) {
        if (cancelPrevious) {
            if (mTracker == null) {
                throw new IllegalStateException();
            } else {
                mTracker.cancelOthers(this);
            }
        }
        mInnerTask.executeOnExecutor(executor, params);
        return this;
    }

    public static void runAsyncParallel(Runnable runnable) {
        runAsynckInternal(PARALLEL_EXECUTOR, runnable);
    }

    public static void runAsyncSerial(Runnable runnable) {
        runAsynckInternal(SERIAL_EXECUTOR, runnable);
    }

    private static void runAsynckInternal(Executor executor, final Runnable runnable) {
        executor.execute(runnable);
    }
}
