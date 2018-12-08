package stas.concurrency.executors;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
public class ThreadPoolExecutor_DEMO {

// https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ThreadPoolExecutor.html
	
	public static void main(String[] args) throws InterruptedException {
		
		ThreadPoolExecutor exec = new ThreadPoolExecutor(30, 200, 10, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(30));
		/*
		 * created new threadPool with characteristics corePoolSize = 5
		 * maximumPoolSize = 10 keepAliveTime = 20 seconds workQueue
		 */

//		exec.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
//		exec.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
//		exec.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
		exec.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		
		for (int i = 0; i < 501; i++) {

			Thread.sleep(75);
			try {
				exec.submit(() -> {
					try {
						Thread.sleep(200);
					} catch (Exception e) {
					}
					;
					System.out.println(Thread.currentThread().getName());
				});
			} catch (RejectedExecutionException e) {
				e.printStackTrace();
			}
		}

		Thread.sleep(4000);
		exec.shutdown();
		Thread.sleep(4000);
		/*
		 * 							Rejected tasks 
		 * New tasks submitted in method execute(Runnable) will
		 * be rejected when the Executor has been shut down, and also when the
		 * Executor uses finite bounds for both maximum threads and work queue
		 * capacity, and is saturated. In either case, the execute method
		 * invokes the RejectedExecutionHandler.rejectedExecution(Runnable,
		 * ThreadPoolExecutor) method of its RejectedExecutionHandler. Four
		 * predefined handler policies are provided: In the default
		 * 		ThreadPoolExecutor.AbortPolicy, the handler throws a runtime
		 * RejectedExecutionException upon rejection. In
		 * 		ThreadPoolExecutor.CallerRunsPolicy, the thread that invokes execute
		 * itself runs the task. This provides a simple feedback control
		 * mechanism that will slow down the rate that new tasks are submitted.
		 * 		In ThreadPoolExecutor.DiscardPolicy, a task that cannot be executed
		 * is simply dropped. 
		 * 		In ThreadPoolExecutor.DiscardOldestPolicy, if the
		 * executor is not shut down, the task at the head of the work queue is
		 * dropped, and then execution is retried (which can fail again, causing
		 * this to be repeated.) It is possible to define and use other kinds of
		 * RejectedExecutionHandler classes. Doing so requires some care
		 * especially when policies are designed to work only under particular
		 * capacity or queuing policies.
		 * 
		 */	
	}

}
