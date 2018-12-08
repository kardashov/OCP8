package stas.concurrency.threads;

class TimeBomb extends Thread {
    String[] timeStr = {"Zero", "One", "Two", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine"};

    public void run() {
        for (int i = 9; i >= 0; i--) {
            try {
                System.out.println(timeStr[i]);
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public static void main(String[] s) {
        TimeBomb timer = new TimeBomb();
        System.out.println("Starting 10 second count down. . . ");
        timer.start();
        try {
            timer.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println("Boom!!!");
    }
}
