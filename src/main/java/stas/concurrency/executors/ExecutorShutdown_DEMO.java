package stas.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorShutdown_DEMO {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(3);

		executor.submit(() -> {
			System.out.println("Hello from task");
			try {Thread.sleep(4000);
			} catch (InterruptedException e) {e.printStackTrace();}
		});
		try {
			System.out.println("attempt to shutdown executor");
			executor.shutdown();
			executor.awaitTermination(3, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println("tasks interrupted");
		} finally {
			if (!executor.isTerminated()) {
				System.err.println("cancel non-finished tasks");
			}
			executor.shutdownNow();
			System.out.println("shutdown finished");
		}
	}

}
