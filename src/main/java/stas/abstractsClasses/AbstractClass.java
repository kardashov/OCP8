package stas.abstractsClasses;
public class AbstractClass { }
/*				Abstract Class Definition Rules:
		1. Abstract classes cannot be instantiated directly.
		2. Abstract classes may be defined with any number, including zero, of abstract and nonabstract
		methods.
		3. Abstract classes may not be marked as private or final.
		4. An abstract class that extends another abstract class inherits all of its abstract methods
		as its own abstract methods.
		5. The first concrete class that extends an abstract class must provide an implementation
		for all of the inherited abstract methods.
		
				Abstract Method Definition Rules:
		1. Abstract methods may only be defined in abstract classes.
		2. Abstract methods may not be declared private or final.
		3. Abstract methods MUST NOT provide a method body/implementation in the abstract
		class for which is it declared.
		4. Implementing an abstract method in a subclass follows the same rules for overriding a
		method. For example, the name and signature must be the same, and the visibility of
		the method in the subclass must be at least as accessible as the method in the parent
		class.  */
////////////////////////////////////////////
//an abstract class is not required to    //
//include any abstract methods.		 	  //
////////////////////////////////////////////
public final abstract class Tortoise { //abstract CLASS can't be defined as FINAL

	public abstract final void chew();} // abstract METHOD can't be defined as FINAL
	private abstract void sing(); // method may not be marked as both abstract and private


class Chicken {
	public abstract void peck(); // DOES NOT COMPILE
	////////////////////////////////////////////////////////////////
	//	abstract method can't be defined in non-abstract class	////
	////////////////////////////////////////////////////////////////
}

abstract class Turtle {
	public abstract void swim() {} // DOES NOT COMPILE
	public abstract int getAge() {return 10;} // DOES NOT COMPILE
	///////////////////////////////////////////////////////////////////
	//////////		abstract method can't have implementation	///////
	///////////////////////////////////////////////////////////////////
}

 abstract class Animal {
	 public abstract String getName();}
 class Walrus extends Animal { // DOES NOT COMPILE
//	Child non-abstract class MUST IMPLEMENT all  INHERITED ABSTRACT methods
}
 abstract class Eagle extends Animal {} 
//////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
 
// concrete subclass is not required to provide an implementation for an abstract
// method if an intermediate abstract class provides the implementation.
abstract class Animal {
	 public abstract String getName();
	 }
abstract class BigCat extends Animal {
	 public String getName() {
	 			return "BigCat";}
	 public abstract void roar();
 	 }
class Lion extends BigCat {
	 public void roar() {
		 System.out.println("The Lion lets out a loud ROAR!");
	 }
	 }
////////////////////////////////////////////////////////////////////////////////////////////// 



