package stas.reflection;

public class Primitives {

    public static void main(String[] args) {

        Class intClass = int.class;
        System.out.println("is primitive: " + intClass.isPrimitive());
        // Integer intInstance = intClass.newInstance(); ������!

    }

}
