package stas.interfaces;

interface Walk {
    public default int getSpeed() {
        return 5;
    }
}

interface Run {
    public default int getSpeed() {
        return 7;
    }
}

interface RunFast extends Run {

    public default int getSpeed() { // ��������������� ���������� ������ run
        return 10;
    }
}

public class InterfaceTest implements Run, RunFast {


    public static void main(String[] args) {
        String Integer = "sdfsdf";

        InterfaceTest it = new InterfaceTest();
        System.out.println(it.getSpeed()); // prints 10

    }
}
