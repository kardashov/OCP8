package stas.variables_operators_loops;

public class FINAL_Keyword {
	private final int volume;
	private final String name = "The Mouse House";
	final int fff;
	
//	final variables can be assigned a value ONLY ONCE in:
//	1)	line of the declaration      or
//	2)	in an instance initializer   or
//	3) 	Constructor

	public FINAL_Keyword(int length, int width, int height) {
		volume = length * width * height;
		volume = 33; //DOES NOT COMPILE
		
	}
	void myMethod() {
		volume = 23; // DOES NOT COMPILE читай 3 правила выше.
	}
}