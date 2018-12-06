package stas.inheritance;
//Hiding Static Methods
//A hidden method occurs when a child class defines a static method with the same name
//and signature as a static method defined in a parent class. Method hiding is similar but
//not exactly the same as method overriding. First, the four previous rules for overriding a
//method must be followed when a method is hidden. In addition, a new rule is added for
//hiding a method, namely that the usage of the static keyword must be the same between
//parent and child classes. The following list summarizes the five rules for hiding a method:

//1. The method in the child class must have the same signature as the method in the parent
//class.
//2. The method in the child class must be at least as accessible or more accessible than the
//method in the parent class.
//3. The method in the child class may not throw a checked exception that is new or
//broader than the class of any exception thrown in the parent class method.
//4. If the method returns a value, it must be the same or a subclass of the method in the
//parent class, known as covariant return types.
//5. The method defined in the child class must be marked as static if it is marked as
//static in the parent class (method hiding). Likewise, the method must not be marked
//as static in the child class if it is not marked as static in the parent class (method
//overriding).
public class STATIC_METHOD_Hiding {

    public static void eat() {
        System.out.println("StaticMethodHiding is eating");
    }

    public static void sneeze() {
        System.out.println("StaticMethodHiding is sneezing");
    }

    public void hibernate() {
        System.out.println("StaticMethodHiding is hibernating");
    }


    public static void main(String[] args) {
        //����������� ������ ������ ���������� �� ����� ������:

        Panda.eat(); //Panda bear is chewing
        STATIC_METHOD_Hiding.eat(); //StaticMethodHiding is eating

        //���� ���-���� ���� ����� �� ������, ��
        //����������� ������ ������ ���������� �� ���� ������ �� ����� ����������.
        //������ ������ ����  ��������� � ���������� �� ����� ���������� ������ �� �������(���� null):
        Panda p = new Panda();
        STATIC_METHOD_Hiding smh = new STATIC_METHOD_Hiding();

        p.eat();    //Panda bear is chewing

        smh.eat();    //StaticMethodHiding is eating
        smh = p;
        smh.eat();  //StaticMethodHiding is eating, �������� �� ��, ��� ������ ��� �������� ������ ���� Panda


        smh = null;         //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        smh.eat(); //StaticMethodHiding is eating

    }
}

class Panda extends STATIC_METHOD_Hiding {

    public static void eat() {
        System.out.println("Panda bear is chewing");
    }

    public void sneeze() { // DOES NOT COMPILE
        System.out.println("Panda bear sneezes quietly");
    }

    public static void hibernate() { // DOES NOT COMPILE
        System.out.println("Panda bear is going to sleep");
    }
//	In this example, sneeze() is marked as static in the parent class but not in the child
//	class. The compiler detects that you�re trying to override a method that should be hidden
//	and generates a compiler error. In the second method, hibernate() is an instance member
//	in the parent class but a static method in the child class. In this scenario, the compiler
//	thinks that you�re trying to hide a method that should be overridden and also generates a
//	compiler error.


}