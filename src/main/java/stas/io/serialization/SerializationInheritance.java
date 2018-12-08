package stas.io.serialization;
import java.io.*;

//Запускаемый класс для теста
public class SerializationInheritance {
	public static void main(String[] arg) {
		try {
			FileOutputStream fos = new FileOutputStream("output.bin");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			Child c = new Child(2);
			c.changeNames();
			System.out.println(c);
			oos.writeObject(c);
			oos.writeObject(new Child2(3, 4));
			oos.close();
			System.out.println("================= Reading objects: ================================");
			
// Deserialization process ========================			
			FileInputStream fis = new FileInputStream("output.bin");
			ObjectInputStream ois = new ObjectInputStream(fis);
			System.out.println(ois.readObject());
			System.out.println(ois.readObject());
			
			ois.close();
		} catch (Exception e) { // упрощенная обработка для краткости
			e.printStackTrace();
		}
	}
}

//============ Родительский класс, не реализующий Serializable ========================
class Parent {
	public String firstName;
	private String lastName;

	public Parent() {
		System.out.println("Constructor Parent");
		firstName = "old_first";
		lastName = "old_last";
	}

	public void changeNames() {
		firstName = "new_first";
		lastName = "new_last";
	}

	public String toString() {
		return super.toString() + ",first=" + firstName + ",last=" + lastName;
	}
}
// ========= Класс Child, впервые реализовавший Serializable =============================
class Child extends Parent implements Serializable {
	private int age;

	public Child(int age) {
		System.out.println("Constructor Child");
		this.age = age;
	}

	public String toString() {
		return super.toString() + ",age=" + age;
	}
}
// ================ Наследник Serializable-класса ========================================
class Child2 extends Child {
	private int size;

	public Child2(int age, int size) {
		super(age);
		System.out.println("Constructor Child2");
		this.size = size;
	}

	public String toString() {
		return super.toString() + ",size=" + size;
	}
}
//Constructor Parent
//Constructor Child
//stas.io.serialization.Child@4aa298b7,first=new_first,last=new_last,age=2
//Constructor Parent
//Constructor Child
//Constructor Child2
//================= Reading objects: ================================
//Constructor Parent
//stas.io.serialization.Child@6d03e736,first=old_first,last=old_last,age=2
//Constructor Parent
//stas.io.serialization.Child2@568db2f2,first=old_first,last=old_last,age=3,size=4
// http://old.intuit.ru/department/pl/javapl/15/3.html
//Видно, что для объектов, порожденных от Serializable -классов, конструкторы не вызываются вовсе. 
//Идет обращение лишь к конструктору без параметров не- Serializable -суперкласса.








