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
// ������ ������ � Java ����� �� ������ ���������� ��� synchronized ������ �
// �������, �� � ��� ���������� wait-set, ����� ������� ����������. ����� ����� �����
// ������� ����� wait() ������ ������� � ����� ������� ������� � ��� wait-set.
// ��� ���� ���������� ������ ������ ������������������ �� ��� ���,
// ���� ������ ����� �� ������� � ����� �� ������� ����� notifyAll(),
// ������� ���������� ��� ������ �� wait-set. ����� notify() ���������� ����
// �������� ��������� ����� �� ������� ������.
//
// ������ ���������� ���� ������� ������� � ����� ������ ������������.
// ����� �� ��� ����� ���� ������ ������� � ������� ������ ����� ������������
// ���������� �� ���� ������. �� ���� ���� ������ synchronized -����� � ������� �� ���� ������
// � �������� ���������, ���� ��������� � ������� ������ ���� �
// ������������������ ������� ������ ������ �������.
//
