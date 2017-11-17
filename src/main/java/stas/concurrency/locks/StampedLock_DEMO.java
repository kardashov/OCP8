package stas.concurrency.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

public class StampedLock_DEMO {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		StampedLock lock = new StampedLock();
		// An optimistic read lock is acquired by calling tryOptimisticRead()
		// which always returns a stamp without blocking the current thread, no
		// matter if the lock is actually available. If there's already a write
		// lock active the returned stamp equals zero. You can always check if a
		// stamp is valid by calling lock.validate(stamp).
		
		// The optimistic lock is valid right after acquiring the lock. In
		// contrast to normal read locks an optimistic lock doesn't prevent
		// other threads to obtain a write lock instantaneously. After sending
		// the first thread to sleep for one second the second thread obtains a
		// write lock without waiting for the optimistic read lock to be
		// released. From this point the optimistic read lock is no longer
		// valid. Even when the write lock is released the optimistic read locks
		// stays invalid.

		// So when working with optimistic locks you have to validate the lock
		// every time after accessing any shared mutable variable to make sure
		// the read was still valid.
		executor.submit(() -> {
		    long stamp = lock.tryOptimisticRead();
		    try {
		        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
		        Thread.sleep(1);
		        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
		        Thread.sleep(2);
		        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
		    } catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
		        lock.unlock(stamp);
		    }
		});

		executor.submit(() -> {
		    long stamp = lock.writeLock();
		    try {
		        System.out.println("Write Lock acquired");
		        Thread.sleep(2);
		    } catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
		        lock.unlock(stamp);
		        System.out.println("Write done");
		    }
		});

		executor.shutdown();
		Thread.sleep(3000);
		
		System.out.println("=========  Converting readLock to WriteLock =============");
		executor = Executors.newFixedThreadPool(2);
		// The task first obtains a read lock and prints the current value of
		// field count to the console. But if the current value is zero we want
		// to assign a new value of 23. We first have to convert the read lock
		// into a write lock to not break potential concurrent access by other
		// threads. Calling tryConvertToWriteLock() doesn't block but may return
		// a zero stamp indicating that no write lock is currently available. In
		// that case we call writeLock() to block the current thread until a
		// write lock is available.
		
		executor.submit(() -> { 
			int count = 0;
		    long stamp = lock.readLock();
		    try { 
		        if (count == 0) {
		            stamp = lock.tryConvertToWriteLock(stamp);
		            System.out.println("Lock stamp is " + stamp);
		            if (stamp == 0L) {
		                System.out.println("Could not convert to write lock");
		                stamp = lock.writeLock();
		            }
		            count = 23;
		        }
		        System.out.println(count);
		    } finally {
		        lock.unlock(stamp);
		    }
		});
		
		executor.shutdown();
	}
}
