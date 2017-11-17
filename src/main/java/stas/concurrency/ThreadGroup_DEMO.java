package stas.concurrency;
/* ThreadGroup

 Для того, чтобы отдельный поток не мог начать останавливать и прерывать
 все потоки подряд, введено понятие группы. Поток может оказывать влияние
 только на потоки, которые находятся в одной с ним группе. Группу потоков
 представляет класс ThreadGroup. Такая организация позволяет защитить
 потоки от нежелательного внешнего воздействия. Группа потоков может
 содержать другие группы, что позволяет организовать все потоки и группы в
 иерархическое дерево, в котором каждый объект ThreadGroup, за исключением
 корневого, имеет родителя.

 Класс ThreadGroup обладает методами для изменения свойств всех входящих в него потоков,
таких, как приоритет, daemon и т.д. Метод list() позволяет получить список потоков.*/

public class ThreadGroup_DEMO implements Runnable {

	// Отдельная группа, в которой будут
	// находиться все потоки ThreadTest
	public final static ThreadGroup GROUP = new ThreadGroup("Daemon demo");

	
	private int start;// Стартовое значение, указывается при создании объекта

	public ThreadGroup_DEMO(int s) {
		start = (s % 2 == 0) ? s : s + 1;
		new Thread(GROUP, this, "Thread " + start).start();
	}

	public void run() {
		// Начинаем обратный отсчет
		for (int i = start; i > 0; i--) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
			}
			// По достижении середины порождаем
			// новый поток с половинным начальным
			// значением
			if (start > 2 && i == start / 2) {
				new ThreadGroup_DEMO(i);
			}
		}
	}

	public static void main(String s[]) {
		new ThreadGroup_DEMO(16);
		new DaemonDemo();
	}
}

class DaemonDemo extends Thread {
	public DaemonDemo() {
		super("Daemon demo thread");
		setDaemon(true);
		start();
	}

	public void run() {
		Thread threads[] = new Thread[10];
		while (true) {
			// Получаем набор всех потоков из
			// тестовой группы
			int count = ThreadGroup_DEMO.GROUP.activeCount();
			if (threads.length < count)
				threads = new Thread[count + 10];
			count = ThreadGroup_DEMO.GROUP.enumerate(threads);

			// Распечатываем имя каждого потока
			for (int i = 0; i < count; i++) {
				System.out.print(threads[i].getName() + ", ");
			}
			System.out.println();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
			}
		}
	}
}