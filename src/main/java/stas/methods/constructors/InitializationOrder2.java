package stas.methods.constructors;

public class InitializationOrder2 {
	private String name = "Torchie";
	{
		System.out.println(name);
	}
	private static int COUNT = 0;
	static {
		System.out.println(COUNT);
	}
	{
		COUNT++;
		System.out.println(COUNT);
	}

	public InitializationOrder2() {
		System.out.println("constructor");
	}

	public static void main(String[] args) {
		System.out.println("read to construct");
		new InitializationOrder2();
	}
}
//0     -- метод main находитс€ в загружаемом классе, сначала инициализруетс€ static переменные и static-блоки
					//а затем вызываетс€ main и т.д....
//read to construct  
//Torchie
//1
//constructor

//Rule 1 doesnТt apply because there is no superclass. Rule 2 tells us to look at the
//static variables and static initializersЧlines 4 and 5, in that order. Line 5 outputs 0.
//Now that the statics are out of the way, the main() method can run. Next, we can use rule
//3 to run the instance variables and instance initializers. Here that is lines 2 and 3, which
//output Torchie. Finally, rule 4 says to run the constructorЧin this case, lines 7Ц9, which
//output constructor.
