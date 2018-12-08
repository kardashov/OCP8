package stas.variables_operators_loops;

import java.io.File;

public class OperatorsAndStatements {
	// ====================================OPERANDS EVALUATION ORDER============================================
	//			http://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.7

	public static void main(String[] args) {
		
	{	int x = 6;
		boolean y = (x >= 6) || (++x <= 7);
		System.out.println(x); //  6     
//		Because x >= 6 is true, the increment operator on the right-hand 
//		side of the expression is never evaluated, so the output is 6.
	}
	   System.out.println(null + true); // DOES NOT COMPILE
	   System.out.println(true + null); // DOES NOT COMPILE
	   System.out.println(null + null); // DOES NOT COMPILE
	{
		boolean x = true == 3; // DOES NOT COMPILE
		boolean y = false != "Giraffe"; // DOES NOT COMPILE
		boolean z = 3 == "Kangaroo"; // DOES NOT COMPILE
	}
	
	{
		boolean y = false;
		boolean x = (y = true);
		System.out.println(x); // Outputs true
	}
	{	
		File x = new File("myFile.txt");
		File y = new File("myFile.txt");
		File z = x;
		System.out.println(x == y); // Outputs false
		System.out.println(x == z); // Outputs true	
	}	
	
	{	
		int x = 1;
		if(x) { // DOES NOT COMPILE,  support only boolean statements
				}
	}	
	System.out.println("=========================Ternary operator==============================");
	{
/*		ќператор ? не расшир€ет тип возвращаемого значени€ без надобности!!!
 * 		<boolean>? <byte>:<byte>	 => <byte>
 * 		<boolean>? <byte>:<long>	 => <long>
 * 		<boolean>? <boolean>:<boolean>	 => <boolean>
		<boolean>? <long>:<long>	 => <long>
		<boolean>? <float>:<double>	 => <double>
		<boolean>? (byte)10 : 10+23  => <byte>
		<boolean>? 		 10 : 10+23	 => <int>    !!!!!!!!
		<boolean>? <Byte>:<int>	 	=> <int>
		<SomeClass3>? <SomeClass1>:<SomeClass2>	 	=> Classes must be compatible
		 */
		int y = 10;
		int x = (y > 5) ? (2 * y) : (3 * y);
		
		System.out.println((y > 5) ? 21 : "Zebra");
		int animal = (y < 91) ? 9 : "Horse"; // DOES NOT COMPILE 
	}	
		
	{	
		int y = 1;
		int z = 1;
		final int x = y<10 ? y++ : z++;
		System.out.println(y+","+z); // Outputs 2,1   выполн€етс€ только один из операторов
	}	
	{	
		int y = 1;
		int z = 1;
		
		final int x = y>=10 ? y++ : z++;
		System.out.println(y+","+z); // Outputs 1,2
//		Now that the left-hand boolean expression evaluates to false, only z was incremented.
//		In this manner, we see how the expressions in a ternary operator may not be applied if
//		the particular expression is not used.
	}	
		
		
		for(int i = 0; i < 10; i++) {
				System.out.print(i + " ");
			}

	{	
		int x = 0;
		for	(long y = 0, z = 4; x < 5 && y < 10; x++, y++) {
				System.out.print(y + " ");
		}
		System.out.print(x);
	}	
	
System.out.println("=========================Switch statement==============================");
//The following method will compile and run without any problems.
	{
	 byte x = 3;
	 	switch(x){
	 			case 'b':   // 1 this constant can be assigned to x
	 			default :   // 2
	 			case -2:    // 3 this constant can be assigned to x
	 			case 80:    // 4 this constant can be assigned to x
	 }
	}
	{	
		int dayOfWeek = 5;
//		Integer dayOfWeek = 1; but this DOES NOT COMPILE, char can't be assigned to Integer
		switch(dayOfWeek) {
		case 0:
			System.out.println("Sunday");
		default:
			System.out.println("Weekday");
		case 6:
			System.out.println("Saturday");
		case 'w':  	 // case constant MUST BE ASSIGNABLE to SWITCH variable
			break;
		} 
//		the case statement value must be a literal, enum constant, or final constant variable.
	}
}// end of method
	
private int getSortOrder(String firstName, final String lastName) {
		String middleName = "Patricia";
		final String suffix = "JR";
		int id = 0;
		switch(firstName) {
		case "Test":	return 52;
		case middleName: // DOES NOT COMPILE
			id = 5;
			break;
		case suffix:
			id = 0;
			break;
		case lastName: // DOES NOT COMPILE
			id = 8;
			break;
		case 5: // DOES NOT COMPILE
			id = 7;
			break;
		case 'J': // DOES NOT COMPILE
			id = 10;
			break;
		case java.time.DayOfWeek.SUNDAY: // DOES NOT COMPILE
			id=15;
			break;
			}
			return id;
//////////////////////////////////////////////////////////////////////////////////////////////////////////		
//////////////////////		SWITCH statement rules				//////////////////////////////////////////			
//////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/*Data types supported by switch statements include the following:
				int and Integer
				byte and Byte
				short and Short
				char and Character
				int and Integer
				String
				enum values             LONG is NOT SUPPORTED
	The values in each case statement MUST BE CONSTANT-TIME CONSTANT VALUES of the same data
type as the switch value. This means you can use only literals, enum constants, or final
constant variables of the same data type.
			*/
			
//			The first case statement compiles without issue using a String literal and is a good
//			example of how a return statement, like a break statement, can be used to exit the switch
//			statement early. The second case statement does not compile because middleName is not a
//			final variable, despite having a known value at this particular line of execution. The third
//			case statement compiles without issue because suffix is a final constant variable.
//			In the fourth case statement, despite lastName being final, it is not constant as it is
//			passed to the function; therefore, this line does not compile as well. Finally, the last three
//			case statements donТt compile because none of them have a matching type of String; the
//			last one is an enum value.
			
			
}

}
