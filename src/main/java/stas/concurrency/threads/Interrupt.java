package stas.concurrency.threads;

public class Interrupt {

	
//	The Interrupt Status Flag
//
//	The interrupt mechanism is implemented using an internal flag known as the interrupt status. 
//	Invoking Thread.interrupt sets this flag. When a thread checks for an interrupt by invoking 
//	the static method Thread.interrupted, interrupt status is cleared. The non-static
//	isInterrupted method, which is used by one thread to query the interrupt status of another, 
//	does not change the interrupt status flag.
//
//	By convention, any method that exits by throwing an InterruptedException clears 
//	interrupt status when it does so. However, it's always possible that interrupt status 
//	will immediately be set again, by another thread invoking interrupt.
	
//	Like sleep, join responds to an interrupt by exiting with an InterruptedException.
}
