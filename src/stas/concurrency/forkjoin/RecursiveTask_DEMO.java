package stas.concurrency.forkjoin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

class ComplicatedTask extends RecursiveTask<Integer> {
	int[] ia;
	int from;
	int to;
	static AtomicInteger invoke_count = new AtomicInteger();;
/*	if (my portion of the work is small enough)
		  do the work directly
		else
		  split my work into two pieces
		  invoke the two pieces and wait for the results*/
	
	static final int THRESHOLD = 50;

	public ComplicatedTask(int[] ia, int from, int to) {
		this.ia = ia;
		this.from = from;
		this.to = to;
	}

	protected Integer compute() {
		System.out.println(invoke_count.incrementAndGet());
		int sum = 0;
		if (from + THRESHOLD > to) {
			for (int i = from; i <= to; i++) {
				sum = sum + ia[i];
			}
//			System.out.println(sum);
			return sum;
		} else {
			int mid = (from + to) / 2;
			ComplicatedTask newtask1 = new ComplicatedTask(ia, from, mid);
			ComplicatedTask newtask2 = new ComplicatedTask(ia, mid + 1, to);
			newtask2.fork();
			
			sum = newtask1.compute();
			sum += newtask2.join();
			
			return sum;
		}
	}
}
 public class RecursiveTask_DEMO {
	 
	 public static void main(String[] args) {
		 int[] f = new int[100000];
		 Arrays.fill(f, 1);
		 
		 ComplicatedTask fb = new ComplicatedTask(f, 1, f.length-1);
		 
		 ForkJoinPool pool = new ForkJoinPool();
		 //Run the task.
		 pool.invoke(fb);
		
	}
	 
 }

