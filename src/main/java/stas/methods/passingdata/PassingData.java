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
//		or reference to a parameter doesnТt change the caller. Calling methods on a reference to an
//		object does affect the caller.
	}
	public static void speak(String name) {
		name = "Sparky"; //создает новый объект String, но при этом модифицирует только ¬Ќ”“–≈ЌЌёё ссылку (параметр)
	}
	
	public static void speak1(StringBuilder s) {
		s.append("Webby"); // вызывает метод, измен€ет состо€ние объекта, а не ссылки
		}

	public static void newNumber(int num) {
		num = 8; //не измен€ет
	}
}
