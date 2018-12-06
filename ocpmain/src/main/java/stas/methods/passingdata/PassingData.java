package stas.methods.passingdata;

public class PassingData {

    public static void main(String[] args) {
        String name = "Webby";
        speak(name);
        System.out.println(name); //Webby

        StringBuilder name1 = new StringBuilder();
        speak1(name1);
        System.out.println(name1); // Webby

        int num = 4;
        newNumber(5);
        System.out.println(num); // 4
//		Assigning a new primitive
//		or reference to a parameter doesn�t change the caller. Calling methods on a reference to an
//		object does affect the caller.
    }

    public static void speak(String name) {
        name = "Sparky"; //������� ����� ������ String, �� ��� ���� ������������ ������ ���������� ������ (��������)
    }

    public static void speak1(StringBuilder s) {
        s.append("Webby"); // �������� �����, �������� ��������� �������, � �� ������
    }

    public static void newNumber(int num) {
        num = 8; //�� ��������
    }
}
