package stas.concurrency;

public class ThreadWait_DEMO implements Runnable {
	private Object shared;

	public ThreadWait_DEMO(Object o) {
		shared = o;
	}

	public void run() {
		synchronized (shared) {
			try {
				shared.wait();
			} catch (InterruptedException e) {
			}
			System.out.println("after wait");
		}
	}

	public static void main(String s[]) {
		Object o = new Object();
		ThreadWait_DEMO w = new ThreadWait_DEMO(o);
		new Thread(w).start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		System.out.println("before notify");
		synchronized (o) {
			o.notifyAll();
		}
	}
}
// http://howtodoinjava.com/core-java/multi-threading/difference-between-yield-and-join-in-threads-in-java/
// Каждый объект в Java имеет не только блокировку для synchronized блоков и
// методов, но и так называемый wait-set, набор потоков исполнения. Любой поток может
// вызвать метод wait() любого объекта и таким образом попасть в его wait-set.
// При этом выполнение такого потока приостанавливается до тех пор,
// пока другой поток не вызовет у этого же объекта метод notifyAll(),
// который пробуждает все потоки из wait-set. Метод notify() пробуждает один
// случайно выбранный поток из данного набора.
//
// Однако применение этих методов связано с одним важным ограничением.
// Любой из них может быть вызван потоком у объекта только после установления
// блокировки на этот объект. То есть либо внутри synchronized -блока с ссылкой на этот объект
// в качестве аргумента, либо обращения к методам должны быть в
// синхронизированных методах класса самого объекта.
//
