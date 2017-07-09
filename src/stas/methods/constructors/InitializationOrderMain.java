package stas.methods.constructors;

public class InitializationOrderMain {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("read to construct");
		InitializationOrderSimple init = new InitializationOrderSimple();
	}
}
/////////////////////////////////////////////////////////////////////////////////////////////
////////////====================CLASS INITITALIZATION RULES==================////////////////
/////////////////////////////////////////////////////////////////////////////////////////////

//1) If there is a superclass, initialize it first; 

//2) Static variable declarations and static initializers in the order they appear in the file;

//3) Instance variable declarations and instance initializers in the order they appear in the file;

//4) The constructor.

public static void main(String[] args) { }
//							ExceptionInInitializerError
//Java runs static initializers the first time a class is used. If one of the static initializers
//throws an exception, Java canТt start using the class. It declares defeat by throwing an
//ExceptionInInitializerError. This code shows an ArrayIndexOutOfBounds in a static
//initializer:
//static {
//int[] countsOfMoose = new int[3];
//int num = countsOfMoose[-1];
//}
//
//This code yields information about two exceptions:
//Exception in thread "main" java.lang.ExceptionInInitializerError
//Caused by: java.lang.ArrayIndexOutOfBoundsException: -1

/////////////////////////////////////////////////////////////////////////////////////////////
//The ExceptionInInitializerError is an error because Java failed to load the whole	/////////
//class. This failure prevents Java from continuing!!!								/////////
/////////////////////////////////////////////////////////////////////////////////////////////

class InitializationOrderSimple {
	private String name = "Torchie";
	{
		System.out.println(name);
	}
	private static int COUNT = 0;
	
	static {
		System.out.println(COUNT);
	}
	static {
		COUNT += 10;
		System.out.println(COUNT);
	}

	public InitializationOrderSimple() {
		System.out.println("constructor");
	}
}
//The output is:
//read to construct    -- т.к. метод main в отдельном классе, см. также InitializationOrder2 
//0
//10
//Torchie
//constructor



