
package com.dove.common.content;

import android.os.AsyncTask;

import java.util.LinkedList;
import java.util.concurrent.Executor;

public abstract class DoveAsyncTask<Params, Progress, Result> extends
        AsyncTask<Params, Progress, Result> {
    /**
     * A collection that's used to record the working DoveAsyncTask. Use can
     * invoke {@link Tracker#interrupt()} to cancel all the background working
     * task.
     */
    public static class Tracker {
        /**
         * Ordered list that's used to record the working DoveAsyncTask.
         */
        private final LinkedList<DoveAsyncTask<?, ?, ?>> mTasks = new LinkedList<DoveAsyncTask<?, ?, ?>>();

        /**
         * Cancel all the task in the tracker.
         */
        public void interrupt() {
            synchronized (mTasks) {
                for (DoveAsyncTask<?, ?, ?> task : mTasks) {
                    task.cancel(true);
                }
                mTasks.clear();
            }
        }

        /**
         * Add a {@link DoveAsyncTask} object to the end of the Tracker Tasks
         * List.
         * 
         * @param task the task to add.
         */
        private void add(DoveAsyncTask<?, ?, ?> task) {
            synchronized (mTasks) {
                mTasks.add(task);
            }
        }

        /**
         * Removes the first occurrence of the specified object from this List.
         * 
         * @param task the task to remove.
         */
        private void remove(DoveAsyncTask<?, ?, ?> task) {
            synchronized (mTasks) {
                mTasks.remove(task);
            }
        }
    }

    /**
     * Record the current working tasks.
     */
    private final Tracker mTracker;

    public DoveAsyncTask(Tracker tracker) {
        mTracker = tracker;
    }

    private void registerSelf() {
        if (mTracker != null) {
            mTracker.add(this);
        }
    }

    private void unregisterSelf() {
        if (mTracker != null) {
            mTracker.remove(this);
        }
    }

    @Override
    protected void onCancelled(Result result) {
        super.onCancelled(result);
        unregisterSelf();
    }

    @Override
    protected final void onPostExecute(Result result) {
        super.onPostExecute(result);
        unregisterSelf();
        if (isCancelled()) {
            onCancelled(result);
        } else {
            onSuccess(result);
        }
    }

    /**
     * Runs on the UI thread after {@link #doInBackground}.
     * <p>
     * This method won't be invoked if the task was cancelled, even if the
     * cancel task operation is called after {@link #doInBackground}.
     * </p>
     * 
     * @param result The result of the operation computed by
     *            {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    protected void onSuccess(Result result) {
    }

    /**
     * Executes the task with the specified parameters in parallel order with
     * other tasks. The task returns itself (this) so that the caller can keep a
     * reference to it. *
     * <p>
     * This method must be invoked on the UI thread.
     * 
     * @param params The parameters of the task.
     * @return This instance of DoveAsyncTask.
     * @throws IllegalStateException If {@link #getStatus()} returns either
     *             {@link AsyncTask.Status#RUNNING} or
     *             {@link AsyncTask.Status#FINISHED}.
     * @see DoveAsyncTask#executeSerial(Object...)
     * @see DoveAsyncTask#cancelAndExecuteParallel(Object...)
     * @see DoveAsyncTask#cancelAndExecuteSerial(Object...)
     * @see DoveAsyncTask#executeParallel(Runnable)
     * @see DoveAsyncTask#executeSerial(Runnable)
     */
    public final DoveAsyncTask<Params, Progress, Result> executeParallel(Params... params) {
        return execute(AsyncTask.THREAD_POOL_EXECUTOR, false, params);
    }

    /**
     * Executes the task with the specified parameters in parallel order with
     * other tasks, and cancel all other existed same task(
     * <em>Instanced with the same class</em>) if they are running. The task
     * returns itself (this) so that the caller can keep a reference to it. *
     * <p>
     * This method must be invoked on the UI thread.
     * 
     * @param params The parameters of the task.
     * @return This instance of DoveAsyncTask.
     * @throws IllegalStateException If {@link #getStatus()} returns either
     *             {@link AsyncTask.Status#RUNNING} or
     *             {@link AsyncTask.Status#FINISHED}.
     * @see DoveAsyncTask#executeParallel(Object...)
     * @see DoveAsyncTask#executeSerial(Object...)
     * @see DoveAsyncTask#cancelAndExecuteSerial(Object...)
     * @see DoveAsyncTask#executeParallel(Runnable)
     * @see DoveAsyncTask#executeSerial(Runnable)
     */
    public final DoveAsyncTask<Params, Progress, Result> cancelAndExecuteParallel(Params... params) {
        return execute(AsyncTask.THREAD_POOL_EXECUTOR, true, params);
    }

    /**
     * Executes the task with the specified parameters in serial order with
     * other tasks. The task returns itself (this) so that the caller can keep a
     * reference to it. *
     * <p>
     * This method must be invoked on the UI thread.
     * 
     * @param params The parameters of the task.
     * @return This instance of DoveAsyncTask.
     * @throws IllegalStateException If {@link #getStatus()} returns either
     *             {@link AsyncTask.Status#RUNNING} or
     *             {@link AsyncTask.Status#FINISHED}.
     * @see DoveAsyncTask#executeParallel(Object...)
     * @see DoveAsyncTask#cancelAndExecuteParallel(Object...)
     * @see DoveAsyncTask#cancelAndExecuteSerial(Object...)
     * @see DoveAsyncTask#executeParallel(Runnable)
     * @see DoveAsyncTask#executeSerial(Runnable)
     */
    public final DoveAsyncTask<Params, Progress, Result> executeSerial(Params... params) {
        return execute(AsyncTask.SERIAL_EXECUTOR, false, params);
    }

    /**
     * Executes the task with the specified parameters in serial order with
     * other tasks, and cancel all other existed same task(
     * <em>Instanced with the same class</em>) if they are running. The task
     * returns itself (this) so that the caller can keep a reference to it. *
     * <p>
     * This method must be invoked on the UI thread.
     * 
     * @param params The parameters of the task.
     * @return This instance of DoveAsyncTask.
     * @throws IllegalStateException If {@link #getStatus()} returns either
     *             {@link AsyncTask.Status#RUNNING} or
     *             {@link AsyncTask.Status#FINISHED}.
     * @see DoveAsyncTask#executeParallel(Object...)
     * @see DoveAsyncTask#executeSerial(Object...)
     * @see DoveAsyncTask#cancelAndExecuteParallel(Object...)
     * @see DoveAsyncTask#executeParallel(Runnable)
     * @see DoveAsyncTask#executeSerial(Runnable)
     */
    public final DoveAsyncTask<Params, Progress, Result> cancelAndExecuteSerial(Params... params) {
        return execute(AsyncTask.SERIAL_EXECUTOR, true, params);
    }

    private DoveAsyncTask<Params, Progress, Result> execute(Executor executor, boolean interrupt,
            Params... params) {
        if (interrupt) {
            if (mTracker == null) {
                throw new IllegalStateException();
            } else {
                mTracker.interrupt();
            }
        }
        // Here, register itself into tracker. And unregister itself in
        // onCancelled method or after tasks finished.
        registerSelf();
        executeOnExecutor(executor, params);
        return this;
    }

    /**
     * Execute the runnable in parallel order.
     * 
     * @param runnable A {@link Runnable} to be executed.
     * @see DoveAsyncTask#executeParallel(Object...)
     * @see DoveAsyncTask#executeSerial(Object...)
     * @see DoveAsyncTask#cancelAndExecuteParallel(Object...)
     * @see DoveAsyncTask#cancelAndExecuteSerial(Object...)
     * @see DoveAsyncTask#executeSerial(Runnable)
     */
    public static void executeParallel(Runnable runnable) {
        execute(AsyncTask.THREAD_POOL_EXECUTOR, runnable);
    }

    /**
     * Execute the runnable in serial order.
     * 
     * @param runnable A {@link Runnable} to be executed.
     * @see DoveAsyncTask#executeParallel(Object...)
     * @see DoveAsyncTask#executeSerial(Object...)
     * @see DoveAsyncTask#cancelAndExecuteParallel(Object...)
     * @see DoveAsyncTask#cancelAndExecuteSerial(Object...)
     * @see DoveAsyncTask#executeParallel(Runnable)
     */
    public static void executeSerial(Runnable runnable) {
        execute(AsyncTask.SERIAL_EXECUTOR, runnable);
    }

    private static void execute(Executor executor, final Runnable runnable) {
        executor.execute(runnable);
    }
}
