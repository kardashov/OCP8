package stas.concurrency;
//SEMAPHORE controls access to one or more shared resources.
//https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Semaphore.html

//PHASER is used to support a synchronization barrier.
//https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Phaser.html

//COUNTDOWNLATCH allows threads to wait for a countdown to complete.
//https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CountDownLatch.html

//EXCHANGER supports exchanging data between two threads.
//https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Exchanger.html

//CYCLICBARRIER enables threads to wait at a predefined execution point.
//https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CyclicBarrier.html

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class _MainConcurrencyTest {

    public static void main(String[] args) {

        Thread currentThread = Thread.currentThread();


        //currentThread.wait(55);//Throws IllegalMonitorStateException
        //wait() can be called only inside synchronized block


        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            System.out.println("begin");
            service.execute(() -> System.out.println("Printing zoo inventory"));
            service.execute(() -> {
                for (int i = 0; i < 3; i++)
                    System.out.println("Printing record: " + i);
            });
            service.execute(() -> System.out.println("Printing zoo inventory"));
            System.out.println("end");
        } finally {
            if (service != null)
                service.shutdown();
        }

        System.out.println("=========================ExecutorService Methods==========================");
/*	====void execute(Runnable command) 
 		Executes a Runnable task at some	point in the future
	====Future<?> submit(Runnable task) 
		Executes a Runnable task at some point in the future and returns a Future representing the task
	====<T> Future<T> submit(Callable<T> task) 
		Executes a Callable task at some point in the future and
		returns a Future representing the pending results of the task
	====<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)	throws InterruptedException 
		Executes the given tasks, synchronously returning the results of all tasks
		as a Collection of Future objects, in the same order they were in the original collection
	====<T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException 
		Executes the given tasks, synchronously returning the result of one of finished tasks, cancelling 
		any unfinished tasks*/
        service = Executors.newSingleThreadExecutor();
        Future<Integer> futureResult = service.submit(() -> 50);     //Callable version: lambda has RETURN
        Future<?> futureResult1 = service.submit(() -> {
            System.out.println("sss");
        });//Runnable version

        // java.util.concurrent.FutureTask is instance of concrete class returned by submit
        System.out.println(futureResult.getClass().getName());// java.util.concurrent.FutureTask

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("futureResult.isDone():" + futureResult.isDone());//true
        System.out.println("futureResult.isCancelled():" + futureResult.isCancelled());//false
        System.out.println("service.isShutdown():" + service.isShutdown());//false
        System.out.println("service.isTerminated():" + service.isTerminated());//false

        try {
            System.out.println(futureResult.get()); //prints 50
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Callable<Integer>> taskList = new ArrayList<>(); //create List of tasks

        for (Integer i : list) {
            service.submit(() -> System.out.println(i)); //submits task one by one
            taskList.add(() -> {
                System.out.println(i);
                return i;
            });// adds task objects to Collection
        }

        try {
            List<Future<Integer>> resultFutureList = service.invokeAll(taskList);//submit all tasks

            int intResult = service.invokeAny(taskList);//returns one result synchronously,
            //and cancels other threads

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("=========================Future<V> Object==========================");

        Future<Integer> result = service.submit(() -> 5);

//		result.cancel(boolean mayInterruptIfRunning);//Attempts to cancel execution of the task.
        result.isDone(); //Returns true if the task was completed, threw an exception, or
        //was cancelled
        result.isCancelled();// Returns true if the task was cancelled before it completed normally.
        try {
            int i = result.get();//Retrieves the result of a task, waiting endlessly if it is not yet available.
            i = result.get(1, TimeUnit.SECONDS);
        }//waits for result some time then throws TimeoutException
        catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        System.out.println("=========================Service Shutdown Process==========================");
        service.shutdown();   //rejects new tasks, Waits until tasks complete.
        service.shutdownNow();//attempts to stop all running tasks and discards any that have
        //not been started yet. shutdownNow() attempts to stop all running tasks

        System.out.println(service.getClass().getName());
        if (service != null) {
            try {
                service.awaitTermination(1, TimeUnit.MINUTES);// Check whether all tasks are finished
//				Blocks until all tasks have completed execution after a shutdown request, 
//				or the timeout occurs, or the current thread is interrupted, whichever happens first.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (service.isTerminated())
                System.out.println("All tasks finished");
            else
                System.out.println("At least one task is still running");
        }
        System.out.println("=========================Scheduling Tasks==========================");
        ScheduledExecutorService serviceScheduled = Executors.newSingleThreadScheduledExecutor();
        Runnable task1 = () -> System.out.println("Hello Zoo");
        Callable<String> task2 = () -> "Monkey";
        Future<?> result1 = serviceScheduled.schedule(task1, 10, TimeUnit.SECONDS);
        Future<?> result2 = serviceScheduled.schedule(task2, 1, TimeUnit.MINUTES);


        serviceScheduled.scheduleAtFixedRate(task1, 5, 1, TimeUnit.SECONDS);
//		scheduleAtFixedRate() method creates a new task and submits it to the executor
//		every period, regardless of whether or not the previous task finished.
        serviceScheduled.scheduleWithFixedDelay(task1, 0, 2, TimeUnit.MINUTES);
//		cheduleAtFixedDelay() method creates a new task after the
//		previous task has finished.
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        serviceScheduled.shutdownNow();


//		Executors.newCachedThreadPool();
//		Executors.newFixedThreadPool(nThreads)

        service.shutdown(); //don't forget to shutdown thread

        System.out.println("=========================Atomic Types==========================");
        AtomicInteger atomInt = new AtomicInteger();
        atomInt.set(5);
        System.out.println(atomInt.getAndSet(15)); //prints 5
        System.out.println(atomInt.get());//prints 15
        System.out.println(atomInt.incrementAndGet());//16
        System.out.println(atomInt.decrementAndGet());//15
        System.out.println(atomInt.getAndIncrement());//15
        System.out.println(atomInt.getAndDecrement());//16

        AtomicReferenceArray<String> atomArray = new AtomicReferenceArray<String>(15);


    }
}
