package stas.methods.constructors;
//LESSON 168
public class Bunny {
	public Bunny() {
		System.out.println("constructor");
	}

	public bunny() { } // DOES NOT COMPILE

	public void Bunny() {
	} // COMPILES, But is not a constructor

	private String color;

	private Bunny(String color) {
		this.color = color;
	}

	public static void main(String[] args) {

		Rabbit1 r1 = new Rabbit1();
		Rabbit2 r2 = new Rabbit2();
		Rabbit3 r3 = new Rabbit3(true);
		Rabbit4 r4 = new Rabbit4(); // DOES NOT COMPILE private constructor
		Bunny b1 = new Bunny("red");

	}
}
class Rabbit1 {}
class Rabbit2 {
	public Rabbit2() {
	}
}
class Rabbit3 {
	public Rabbit3(boolean b) {
	}
}
class Rabbit4 {
	private Rabbit4() {
	}
}
class Hamster {
	private String color;
	private int weight;

	public Hamster(int weight) { // first constructor
		this(weight, "brown"); //this() call must be the first noncommented statement in the constructor.
		Hamster(weight, "brown"); // DOES NOT COMPILE
		new Hamster(weight, "brown");//компилируется, но создает ДРУГОЙ новый объект.
		
		this.weight = weight;
		color = "brown";
	}

	public Hamster(int weight, String color) { // second constructor
		this.weight = weight;
		this.color = color;
	}
}