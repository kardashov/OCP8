package stas.concurrency.threads;

class ThreadStateProblem extends Thread {
    public void run() {
        try {
            System.out.println("Im inside run!");
            wait(1000);
        } catch (InterruptedException ie) {
            // its okay to ignore this exception since we're not
            // interrupting exceptions in this code
            ie.printStackTrace();
        }
    }

    public static void main(String[] s) {
        new ThreadStateProblem().start();
    }
}
// The wait(int) method (with or without timeout value) should be
// called only after acquiring a lock: a wait() call adds
// the thread to the waiting queue of the acquired lock.
// If you don�t do that, there is no proper transition from the
// running state to timed_waiting (or waiting state, if a timeout
// value is not given) to happen. So, the program crashes by
// throwing an IllegalMonitorStateException exception.

// So, the wait(1000); statement behaves almost like a sleep(1000) statement;
// the difference is that calling wait() releases the lock on this object when
// it waits while sleep() call will not release the lock when it sleeps.
