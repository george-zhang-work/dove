package com.dove.common.content;

import android.os.Handler;
import android.os.HandlerThread;

/**
 * Helper class for managing the background thread used to perform io operations and handle async
 * broadcasts.
 */
public class AsyncHandler {
    private static final HandlerThread sHandlerThread = new HandlerThread("AsyncHandler");
    private static final Handler sHandler;

    static {
        sHandlerThread.start();
        sHandler = new Handler(sHandlerThread.getLooper());
    }

    private AsyncHandler() {
    }

    /**
     * Causes the Runnable r to be added to the message queue. The runnable will be run on the
     * background thread to which inner handler is attached.
     *
     * @param r The Runnable that will be executed.
     * @return Returns true if the Runnable was successfully placed in to the message queue.
     * Returns false on failure, usually because the looper processing the message queue is exiting.
     */
    public static boolean post(Runnable r) {
        return sHandler.post(r);
    }

    /**
     * Causes the Runnable r to be added to the message queue, to be run after the specified amount
     * of time elapses. The runnable will be run on the background thread to which inner handler is attached.
     *
     * @param r           The Runnable that will be executed.
     * @param delayMillis The delay (in milliseconds) until the Runnable will be executed.
     * @return Returns true if the Runnable was successfully placed in to the message queue.
     * Returns false on failure, usually because the looper processing the message queue is exiting.
     * <em> Note that a result of true does not mean the Runnable will be processed -- if the looper
     * is quit before the delivery time of the message occurs then the message will be dropped. </em>
     */
    public final boolean postDelayed(Runnable r, long delayMillis) {
        return sHandler.postDelayed(r, delayMillis);
    }
}
