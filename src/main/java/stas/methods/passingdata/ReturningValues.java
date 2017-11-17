package stas.methods.passingdata;

public class ReturningValues {
	public static void main(String[] args) {
		int number = 1; // 1
		String letters = "abc"; // abc
		number(number); // 1
		letters = letters(letters); // abcd
		System.out.println(number + letters); // 1abcd
	}

	public static int number(int number) {
		number++;
		return number;
	}

	public static String letters(String letters) {
		letters += "d";
		return letters;
	}
}
//Lines 3 and 4 are straightforward assignments.
//Line 5 calls a method. Line 10 increments the method parameter to 2 but leaves the
//numbers variable in the main() method as 1. While line 11 returns the value, the caller ignores
//it. The method call on line 6 doesn’t ignore the result so letters becomes "abcd". Remember
//that this is happening because of the returned value and not the method parameter.