package stas.exceptions;
/*								Checked Exceptions
Checked exceptions have Exception in their hierarchy but not RuntimeException. They
must be handled or declared. They can be thrown by the programmer or by the JVM.
Common runtime exceptions include the following:
		FileNotFoundException Thrown programmatically when code tries to reference a file
that does not exist
		IOException Thrown programmatically when thereís a problem reading or writing a file
  									
  									Errors
Errors extend the Error class. They are thrown by the JVM and should not be handled or
declared. Errors are rare, but you might see these:
		ExceptionInInitializerError Thrown by the JVM when a static initializer throws
an exception and doesnít handle it
		StackOverflowError Thrown by the JVM when a method calls itself too many times
(this is called infinite recursion because the method typically calls itself without end)
		NoClassDefFoundError Thrown by the JVM when a class that the code uses is available
at compile time but not runtime 

						Runtime Exceptions
Runtime exceptions extend RuntimeException. They donít have to be handled or declared.
They can be thrown by the programmer or by the JVM. Common runtime exceptions
include the following:
		ArithmeticException Thrown by the JVM when code attempts to divide by zero
		ArrayIndexOutOfBoundsException Thrown by the JVM when code uses an illegal
index to access an array
		ClassCastException Thrown by the JVM when an attempt is made to cast an exception
to a subclass of which it is not an instance
		IllegalArgumentException Thrown by the programmer to indicate that a method has
been passed an illegal or inappropriate argument
		NullPointerException Thrown by the JVM when there is a null reference where an
object is required
		NumberFormatException Thrown by the programmer when an attempt is made to convert
a string to a numeric type but the string doesnít have an appropriate format
*
*/

/*				Any Java type, including Exception, can be declared as the return type!!!!
 * 				However, this will simply return the object rather than throw an exception. */
public class _BasicExceptions {

//	file:\\D:\googledrive\MyLibrary\Enthuware.com\ExceptionClassSummary (106)\ExceptionClassSummary - Enthuware.com.pdf
	public static void main(String[] args) throws Exception {
//		Classes listed in the throws part of a method declaration must extend
//		java.lang.Throwable.
		 try {
		 // protected code
		 } catch (exceptiontype identifier) {
		 // exception handler
		 } finally {
		 // finally block
		 }

		String s = "";
		try {
			s += "t";
			///////////////////////////////////////////////////////////////////
			/////// When System.exit() is called in the try or catch///
			/////// block, finally does not run. ///
			///////////////////////////////////////////////////////////////////
		} catch (Exception e) {
			s += "c";
		} finally {
			s += "f";

		}
		s += "a";
		System.out.print(s); // prints tfa
		/////////////////////////////////////////////////////////////////
		/////////////// HANDLING MULTIPLE EXCEPTION TYPES	/////////////
		/////////////////////////////////////////////////////////////////
		try {
			seeAnimal();
		} catch (AnimalsOutForAWalk e) {// first catch block
			System.out.print("try back later");
		} catch (ExhibitClosed e) {// second catch block
			System.out.print("not today");
		}   // compiles and runs fine

		try {
			seeAnimal();
		} catch (ExhibitClosed e) {
			System.out.print("not today");
		} catch (ExhibitClosedForLunch e) {// DOES NOT COMPILE
			System.out.print("try back later");
		}
		
		// throw new Exception();
		// throw new Exception("Ow! I fell.");
		// throw new RuntimeException();
		// throw new RuntimeException("Ow! I fell.");

	
		try {       /// œŒ–ﬂƒŒ  catch ·ÎÓÍÓ‚ »Ã≈≈“ «Õ¿◊≈Õ»≈...
			seeAnimal();
		} catch (ExhibitClosedForLunch e) {// subclass exception
			System.out.print("try back later");
		} catch (ExhibitClosed e) {// superclass exception
			System.out.print("not today");
		}
		
		try {
			seeAnimal();
		} catch (RuntimeException e) {
			System.out.print("runtime exception");
		} catch (ExhibitClosed e) {// DOES NOT COMPILE, Ú.Í. ›“Œ“  Œƒ Õ» Œ√ƒ¿ Õ≈ ¡”ƒ≈“ »—œŒÀÕ≈Õ
			System.out.print("not today");
		} catch (Exception e) {
			System.out.print("exception");
		}
			
	
//////////////////////////////////////////////////////////////////////////
////////////////// 		FORGOTTEN EXCEPTION 		//////////////////////
//////////////////////////////////////////////////////////////////////////
	try {
throw new RuntimeException(); //27: 
	} catch (RuntimeException e) { //28: 
throw new RuntimeException();  //29: 
	} finally {                    //30: 
throw new Exception();         //31: 
}                              //32: 
/*
 *Line 27 throws an exception, which is caught on line 28. The catch block then throws
an exception on line 29. If there were no finally block, the exception from line 29 would
Recognizing Common Exception Types 313
be thrown. However, the finally block runs after the try block. Since the finally block
throws an exception of its own on line 31, this one gets thrown. The exception from the
catch block gets forgotten about. */


class AnimalsOutForAWalk extends RuntimeException { }
class ExhibitClosed extends RuntimeException { }
class ExhibitClosedForLunch extends ExhibitClosed { }
