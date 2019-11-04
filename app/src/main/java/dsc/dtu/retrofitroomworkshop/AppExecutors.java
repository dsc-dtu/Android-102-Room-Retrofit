package dsc.dtu.retrofitroomworkshop;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A class defining a pool of background threads to execute work on.
 */
public class AppExecutors {

    /**
     * The number of threads in the thread-pool.
     *
     * Default value is the number of available cores + 1
     */
    private static final int numberOfThreads = Runtime.getRuntime().availableProcessors() + 1;

    /**
     * A thread-pool to do work off the main-thread.
     */
    private static final ExecutorService executors = Executors.newFixedThreadPool(numberOfThreads);

    public static ExecutorService getBackgroundExecutor() {
        return executors;
    }

}
