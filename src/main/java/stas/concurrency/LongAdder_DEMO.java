package stas.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class LongAdder_DEMO {

	public static void main(String[] args) throws InterruptedException {
		LongAdder adder = new LongAdder();
		ExecutorService executor = Executors.newFixedThreadPool(2);

		IntStream.range(0, 500000).forEach(i -> executor.submit(adder::increment));
		
		
		executor.shutdown();
		System.out.println(adder.sumThenReset()); // ~ 148485
		Thread.sleep(4000);
		System.out.println(adder.sumThenReset()); // ~ 351515
	}

}
