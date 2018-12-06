package stas.concurrency;

public class ThreadWaitInterrupt_DEMO {

    public static Object lock = new Object();

    static class LongRunThread extends Thread {
        @Override
        public void run() {
            System.out.println("LongRunThread before sync block...");
            synchronized (lock) {
                System.out.println("LongRunThread entered sync block...");
                for (; ; ) {
                    if (interrupted()) {
                        System.out.println("LongRunThread was interrupted. Exiting run()");
                        return;
                    }
                }
            }
        }
    }

    static class WaitingThread extends Thread {
        @Override
        public void run() {
            System.out.println("WaitingThread before sync block...");
            synchronized (lock) {
                System.out.println("WaitingThread entered sync block...");

                try {
                    System.out.println("WaitingThread invoking WAIT()...");
                    lock.wait();
                } catch (InterruptedException e) {
                    System.out.println("WaitingThread inside InterruptedException block...");
                    e.printStackTrace();
                    System.out.println("WaitingThread calls return inside InterruptedException block...");
                    return;
                }
                System.out.println("WaitingThread leaving sync block...");
            }

        }

        ;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread waitRun = new WaitingThread();
        waitRun.start();
        Thread.sleep(500);

        Thread longRun = new LongRunThread();
        longRun.start();
        Thread.sleep(500);

        waitRun.interrupt();//waitRun thread was in WAIT mode when interrupt() happened.
        //NO InterruptedException IS THROWN, because monitor is still
        //acquired by LongRun thread

        longRun.interrupt();// now longRun releases monitor. and waitRun's wait() method throws
        // InterruptedException

    }
}
