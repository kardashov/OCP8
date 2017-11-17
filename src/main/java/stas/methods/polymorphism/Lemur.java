package stas.methods.polymorphism;

class Primate {
	public boolean hasHair() {return true;}
}

interface HasTail {
	public boolean isTailStriped();
}

public class Lemur extends Primate implements HasTail {
	public boolean isTailStriped() {
		return false;
	}

	public int age = 10;

	public static void main(String[] args) {
		Lemur lemur = new Lemur();
		System.out.println(lemur.age); // prints 10
		HasTail hasTail = lemur;
		System.out.println(hasTail.isTailStriped()); // prints false
		Primate primate = lemur;
		System.out.println(primate.hasHair()); // prints true

		Object lemurAsObject = lemur; // отлично компилируется
		/*
		 		1) The type of the object determines which properties exist within
		 the object in memory. 
		 		2) The type of the reference to the object
		 determines which methods and variables are accessible to the Java
		 program.
		 */

		primate = lemur;
		Lemur lemur2 = primate; // DOES NOT COMPILE
		Lemur lemur3 = (Lemur) primate; // castint from SUBclass to SUPERclass
										// requires explicit casting
		System.out.println(lemur3.age);
	}}

/////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////// 			CASTING RULES		 ////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////
/*
  	1) Casting an object from a subclass to a superclass doesn’t require
  an explicit cast. 
  	2) Casting an object from a superclass to a
  subclass requires an explicit cast. 
 	3) The compiler will not allow
 casts to unrelated types. 
 	4) Even when the code compiles without issue, an exception may be thrown 
 	at runtime if the object being cast is not actually an instance of that class.
 */
class Bird {}
class Fish {
	public static void main(String[] args) {
		Fish fish = new Fish(); 
		Bird bird = (Bird) fish; // Cast is not possible    	BECAUSE CLASSes ARE UNRELATED
	}}

class Rodent { }
class Capybara extends Rodent {
	 static void main(String[] args) {
		 	Rodent rodent = new Rodent();
		 	Capybara capybara = (Capybara)rodent; // Throws ClassCastException at runtime
//		 	Although this code will compile without issue, it will throw a
//		 	ClassCastException at runtime since the object being referenced is not an instance of the
//		 	Capybara class.
		 	if(rodent instanceof Capybara) {
		 			capybara = (Capybara)rodent; //не бросает исключение в рантайме из-за проверки
		 		}
 }}
