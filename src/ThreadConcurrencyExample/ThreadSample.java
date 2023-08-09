package ThreadConcurrencyExample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSample {



    private final List<String> threadNames;
    private final ThreadsExampleMode mode;
    private final ReentrantLock lock;



    public ThreadSample(ThreadsExampleMode mode) {
        threadNames = new ArrayList<>();
        this.mode = mode;
        this.lock = new ReentrantLock();
    }



    public void init(String threadName) {
        Thread thread = new Thread(this::workerThread);
        thread.setName(threadName);
        System.out.println("We are in thread: " + Thread.currentThread().getName()
                + " before starting a new thread");
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName()
                + " after starting a new thread");
    }



    private void workerThread() {
        Thread currentThread = Thread.currentThread();
        String threadName = currentThread.getName();
        System.out.println("We are now in thread " + threadName);
        for (int i = 0; i < 1000; i++) {
            try {
                long random = (long) (Math.random() * 100);
                switch (mode) {
                    case NON_SYNC -> nonSync(threadName, random);
                    case WITH_SYNC -> withSync(threadName, random);
                    default -> withLock(threadName, random);
                }
                sleep(currentThread, random / 50);
            } catch (IndexOutOfBoundsException e) {
                System.err.println(">>>>> IndexOutOfBoundsException caught in '" + threadName + "' <<<<<");
                e.printStackTrace();
            }
        }
        String output = String.format("Thread %s. List size is %d", threadName,
                threadNames.size());
        System.out.println(output);
    }



    private void nonSync(String threadName, long random) {
        int size = threadNames.size();
        if ((random % 2 == 0) && (size > 0)) {
            threadNames.remove(size - 1);
        } else {
            threadNames.add(threadName);
        }
    }



    private synchronized void withSync(String threadName, long random) {
        int size = threadNames.size();
        if ((random % 2 == 0) && (size > 0)) {
            threadNames.remove(size - 1);
        } else {
            threadNames.add(threadName);
        }
    }

    private void withLock(String threadName, long random) {
        lock.lock();
        try {
            int size = threadNames.size();
            if ((random % 2 == 0) && (size > 0)) {
                threadNames.remove(size - 1);
            } else {
                threadNames.add(threadName);
            }
        } finally {
            lock.unlock();
        }
    }



    public static void main(String[] args) {
        ThreadSample threads = new ThreadSample(ThreadsExampleMode.WITH_SYNC);
        threads.init("trunk");
        threads.init("branch");
        threads.init("leaf");
        threads.init("twig");
        threads.init("shape");
        threads.init("progress");
        sleep(Thread.currentThread(), 5000);
        System.out.println("MAIN thread. List size is " + threads.threadNames.size());
    }



    private static void sleep(Thread t, long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    enum ThreadsExampleMode {
        NON_SYNC,
        WITH_SYNC,
        WITH_LOCK
    }



}