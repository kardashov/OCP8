
interface Eatable {
	int types = 10;
	static void m1() {};
}

class Food implements Eatable {
	//public static int types = 20;
	static void m1() {};
}

public class Fruit extends Food implements Eatable { // LINE1

	static void m1() {};
	public static void main(String[] args) {
		m1();
		Fruit f = new Fruit();
		System.out.println(f.types);;
		//types = 30; // LINE 2
		System.out.println(types); // LINE 3
	}
}