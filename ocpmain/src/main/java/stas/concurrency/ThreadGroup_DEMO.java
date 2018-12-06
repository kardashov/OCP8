package stas.concurrency;
/* ThreadGroup

 ��� ����, ����� ��������� ����� �� ��� ������ ������������� � ���������
 ��� ������ ������, ������� ������� ������. ����� ����� ��������� �������
 ������ �� ������, ������� ��������� � ����� � ��� ������. ������ �������
 ������������ ����� ThreadGroup. ����� ����������� ��������� ��������
 ������ �� �������������� �������� �����������. ������ ������� �����
 ��������� ������ ������, ��� ��������� ������������ ��� ������ � ������ �
 ������������� ������, � ������� ������ ������ ThreadGroup, �� �����������
 ���������, ����� ��������.

 ����� ThreadGroup �������� �������� ��� ��������� ������� ���� �������� � ���� �������,
�����, ��� ���������, daemon � �.�. ����� list() ��������� �������� ������ �������.*/

public class ThreadGroup_DEMO implements Runnable {

    // ��������� ������, � ������� �����
    // ���������� ��� ������ ThreadTest
    public final static ThreadGroup GROUP = new ThreadGroup("Daemon demo");


    private int start;// ��������� ��������, ����������� ��� �������� �������

    public ThreadGroup_DEMO(int s) {
        start = (s % 2 == 0) ? s : s + 1;
        new Thread(GROUP, this, "Thread " + start).start();
    }

    public void run() {
        // �������� �������� ������
        for (int i = start; i > 0; i--) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
            // �� ���������� �������� ���������
            // ����� ����� � ���������� ���������
            // ���������
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
            // �������� ����� ���� ������� ��
            // �������� ������
            int count = ThreadGroup_DEMO.GROUP.activeCount();
            if (threads.length < count)
                threads = new Thread[count + 10];
            count = ThreadGroup_DEMO.GROUP.enumerate(threads);

            // ������������� ��� ������� ������
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