
package com.dove.common.content;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
<<<<<<< HEAD
=======
import android.os.Handler;
import android.os.Message;

>>>>>>> 2d0fde4ad60e2894bb54e65bcc11ca147519aaf0
import java.util.LinkedList;
import java.util.concurrent.Executor;

/**
 * This class has the same usage with {@link AsyncTask}, but has an extra
 * features: <em>-Bulk cancellation of multiple tasks</em>.
 * @see AsyncTask
 */
@SuppressLint("InlinedApi")
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

    private static final int MESSAGE_DELAY_PARALLEL_RUNNABLE = 0X01;
    private static final int MESSAGE_DELAY_SERIAL_RUNNABLE = 0X02;

    private static final InternalHandler sHandler = new InternalHandler();

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
     * @see DoveAsyncTask#executeParallel(Runnable, long)
     * @see DoveAsyncTask#executeSerial(Runnable, long)
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
     * @see DoveAsyncTask#executeParallel(Runnable, long)
     * @see DoveAsyncTask#executeSerial(Runnable, long)
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
     * @see DoveAsyncTask#executeParallel(Runnable, long)
     * @see DoveAsyncTask#executeSerial(Runnable, long)
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
     * @see DoveAsyncTask#executeParallel(Runnable, long)
     * @see DoveAsyncTask#executeSerial(Runnable, long)
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
     * @see DoveAsyncTask#executeParallel(Runnable, long)
     * @see DoveAsyncTask#executeSerial(Runnable, long)
     */
    public static void executeParallel(Runnable runnable) {
        execute(AsyncTask.THREAD_POOL_EXECUTOR, runnable);
    }

    /**
     * Execute the runnable in parallel order with delayed time milliseconds.
     * 
     * @param runnable A {@link Runnable} to be executed.
     * @param delayMillis Time in milliseconds before the runnable been
     *            executed, but without accuracy.
     * @see DoveAsyncTask#executeParallel(Object...)
     * @see DoveAsyncTask#executeSerial(Object...)
     * @see DoveAsyncTask#cancelAndExecuteParallel(Object...)
     * @see DoveAsyncTask#cancelAndExecuteSerial(Object...)
     * @see DoveAsyncTask#executeSerial(Runnable)
     * @see DoveAsyncTask#executeSerial(Runnable, long)
     */
    public static void executeParallel(Runnable runnable, long delayMillis) {
        final Message msg = sHandler.obtainMessage(MESSAGE_DELAY_PARALLEL_RUNNABLE, runnable);
        sHandler.sendMessageDelayed(msg, delayMillis);
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
     * @see DoveAsyncTask#executeParallel(Runnable, long)
     * @see DoveAsyncTask#executeSerial(Runnable, long)
     */
    public static void executeSerial(Runnable runnable) {
        execute(AsyncTask.SERIAL_EXECUTOR, runnable);
    }

    /**
     * Execute the runnable in serial order with delayed time milliseconds.
     * 
     * @param runnable A {@link Runnable} to be executed.
     * @param delayMillis Time in milliseconds before the runnable been
     *            executed, but without accuracy.
     * @see DoveAsyncTask#executeParallel(Object...)
     * @see DoveAsyncTask#executeSerial(Object...)
     * @see DoveAsyncTask#cancelAndExecuteParallel(Object...)
     * @see DoveAsyncTask#cancelAndExecuteSerial(Object...)
     * @see DoveAsyncTask#executeParallel(Runnable)
     * @see DoveAsyncTask#executeParallel(Runnable, long)
     */
    public static void executeSerial(Runnable runnable, long delayMillis) {
        final Message msg = sHandler.obtainMessage(MESSAGE_DELAY_SERIAL_RUNNABLE, runnable);
        sHandler.sendMessageDelayed(msg, delayMillis);
    }

    private static void execute(Executor executor, final Runnable runnable) {
        executor.execute(runnable);
    }

    private static class InternalHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            final Runnable runnable = (Runnable) msg.obj;
            switch (msg.what) {
                case MESSAGE_DELAY_PARALLEL_RUNNABLE:
                    execute(THREAD_POOL_EXECUTOR, runnable);
                    break;
                case MESSAGE_DELAY_SERIAL_RUNNABLE:
                    execute(SERIAL_EXECUTOR, runnable);
                    break;
            }
        }
    }
}
