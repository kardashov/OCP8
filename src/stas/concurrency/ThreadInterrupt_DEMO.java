package stas.concurrency;

public class ThreadInterrupt_DEMO {

	public static void main(String[] args) {

		Runnable task = () -> {
			for (;;) {

//				if (Thread.currentThread().interrupted()) {
//					System.out.println("Inner thread was interrupted");
//					return;
//				}

				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					System.out.println("Sleep interrupted");
					return;
				}
			}
		};

		Thread t = new Thread(task);
		t.start();
		System.out.println("Started new Thread");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Trying to interrupt");
		t.interrupt();
		System.out.println("end of main task");

	}

}
