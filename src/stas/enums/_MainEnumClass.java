package stas.enums;

enum Season {
	// WINTER, SPRING, SUMMER, Summer, FALL,

	WINTER("Low"), SPRING("Medium"), SUMMER("High"), FALL("Medium") , 
	OTHER_SEASON("High", 22) // using 2-argument constuctor
	{
		public void otherSeasonIntMethod() { //OTHER_SEASON individual method
			System.out.println("inside otherSeasonIntMethod");
		}
	}
	
	//////////
	;//semicolon at the end of the enum values is optional only if the only thing in the enum is that list of values.
	//////////
	private String expectedVisitors;
	private int other_param;
	public static int staticInt = 3;
	public static void myStaticMethod() {System.out.println("Inside myStaticMethod()");}
	private Season(String expectedVisitors) { // constructors are required to be private or package private
		this.expectedVisitors = expectedVisitors;
		System.out.println("constructing.." + this.name());
	}

	private Season(String expectedVisitors, int some_param) { //Second constructor
		this(expectedVisitors);
		this.other_param = some_param;
	}

	public void printExpectedVisitors() {
		System.out.println(expectedVisitors);
	}
	
}

enum Season3 {
	WINTER("sdff") {
		public void printHours() {  //this value has its own implementation of printHours()
			System.out.println("short hours");
		}},
	SUMMER {
		public void printHours() {  //this value has its own implementation of printHours()
			System.out.println("long hours");
		}},
	SPRING, FALL; /// gets default implementation of printHours()
	private Season3(String s){}
	private Season3(){}
	public void printHours() {
		System.out.println("default hours");
	}
}
// You cannot extend enum
// enum ExtendedSeason extends Season { } // DOES NOT COMPILE

public class _MainEnumClass {
	public static void main(String[] args) {

		// every enum extends implicitly from java.lang.Enum
		Season s = Season.SUMMER; //first time we ask for any of the enum values, 
								  //Java constructs ALL of the enum values.
		System.out.println(Season.SUMMER);// SUMMER
		System.out.println(s == Season.SUMMER);// true

		// An enum provides a method to get an array of all of the values. You
		// can use this like any normal array, including in a loop:
		Season[] seasonArray = Season.values(); // Method values() is
												// automatically synthesized by
												// compiler

		for (Season season : Season.values()) {
			System.out.println(season.name() + " " + season.ordinal());
		}

		// You can’t compare an int and enum value directly.
		// Remember that an enum is a type and not an int.
		// if ( Season.SUMMER == 2) {} // DOES NOT COMPILE
		// You can also create an enum from a String . This is helpful when
		// working with older
		// code. The String passed in must MATCH EXACTLY.
		Season s1 = Season.valueOf("SUMMER"); // SUMMER
//		Season s2 = Season.valueOf("summer");// exception
		
		System.out.println("=================== Using enums in switch()============================");
		Season summer = Season.SUMMER;
		switch (summer) {
		case WINTER:
			System.out.println("Get out the sled!");
			break;
		case SUMMER: /// NOT Season.SUMMER!!!!
			System.out.println("Time for the pool!");
			break;
		// case 0: System.out.println("Get out the sled!");// DOES NOT COMPILE

		default:
			System.out.println("Is it summer yet?");
		}
		
		Season.SUMMER.printExpectedVisitors();
		
		
		System.out.println(Season.WINTER.ordinal());	// 0
		System.out.println(Season.SUMMER.ordinal());	// 2 
		System.out.println(Season.SPRING.ordinal());	// 1
		System.out.println(Season.FALL.ordinal()); 		// 3
		System.out.println(Season.OTHER_SEASON.ordinal());// 4
		System.out.println(s.ordinal()); //  2 
		
		Season.myStaticMethod(); 			 //static methods can be invoked directly
		System.out.println(Season.staticInt);//static member variables can be invoked directly
		
		System.out.println(Season.SPRING.getClass());// prints stas.enums.Season
		System.out.println(s.getClass()); 			 // prints stas.enums.Season
		System.out.println(s instanceof java.lang.Enum);   //true
		System.out.println(s instanceof java.lang.Enum<?>);//true
		System.out.println(Season.WINTER instanceof java.lang.Enum<?>);//true
	}
}

