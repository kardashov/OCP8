package stas.access.main.pack;

public class NotSubClassFromSamePackage {

    public void myMethod() {
        System.out.println(varPrivate);
        System.out.println(varPackage);
        System.out.println(varProtected);
        System.out.println(varPublic);

        methodPrivate();
        methodPackage();
        methodProtected();
        methodPublic();
        // ������ ������� ������ MySuperClass �������� �� ��������, �.�. ��� ������������
    }


    public static void main(String[] args) {

        MySuperClass f = new MySuperClass();

        System.out.println(f.varPrivate);
        System.out.println(f.varPackage);
        System.out.println(f.varProtected);
        System.out.println(f.varPublic);

        f.methodPrivate();
        f.methodPackage();
        f.methodProtected();
        f.methodPublic();

    }

}
