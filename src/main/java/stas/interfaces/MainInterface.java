package stas.interfaces;
/*  					
 * 	LESSONS 170 - 178
 * 				Interface rules
	1) Interfaces cannot be instantiated directly.
	2) An interface is not required to have any methods.
	3) An interface may not be marked as final.
	4) All top-level interfaces are assumed to have public or default access, and they must
include the abstract modifier in their definition. Therefore, marking an interface as
private, protected, or final will trigger a compiler error, since this is incompatible
with these assumptions.
	5) All nondefault methods in an interface are assumed to have the modifiers abstract
and public in their definition. Therefore, marking a method as private, protected,
or final will trigger compiler errors as these are incompatible with the abstract and
public keywords.   

	1) Interface VARIABLES are assumed to be public, static, and final. Therefore, marking
a variable as private or protected will trigger a compiler error, as will marking any
variable as abstract.
	2) The value of an interface variable must be set when it is declared since it is marked as final. */
public abstract interface MainInterface {
	
	public static final 	int MINIMUM_DEPTH = 2; 
	/*public static final*/	int MAX_DEPTH = 2; // ќЅј ќЅЏя¬Ћ≈Ќ»я »ƒ≈Ќ“»„Ќџ
							// public abstract final -- необ€зательны, но всегда ѕќƒ–ј«”ћ≈¬јё“—я дл€ —¬ќ…—“¬
						  int MIN_SOMETHING; // DOES NOT compile значени€ ¬—≈√ƒј ƒќЋ∆Ќџ Ѕџ“№ ѕ–»—¬ќ≈Ќџ, т.к. есть final
						  	// The blank final field MIN_SOMETHING may not have been initialized
	public abstract 	int getMaximumDepth();
	/*public static */	int getMaximumDepth1();  
			// public и abstract  -- необ€зательны, но всегда ѕќƒ–ј«”ћ≈¬јё“—я дл€ ћ≈“ќƒќ¬
	
}

final interface WalksOnEightLegs { // DOES NOT COMPILE
//	only public & abstract are permitted
}

private final interface CanCrawl { // DOES NOT COMPILE
	private void dig(int depth); // DOES NOT COMPILE
	protected abstract double depth(); // DOES NOT COMPILE
	public final void surface(); // DOES NOT COMPILE
}
/////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////// INTERFACE INHERITANCE //////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////
interface HasTail {
	public int getTailLength();
}
interface HasWhiskers {
	public int getNumberOfWhiskers();
}

interface Seal extends HasTail, HasWhiskers {
}

abstract class HarborSeal implements HasTail, HasWhiskers {
}

class LeopardSeal implements HasTail, HasWhiskers { // DOES NOT COMPILE
}
/////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////			INTERFACE MULTIPLE INHERITANCE				/////////////////
/////////////////////////////////////////////////////////////////////////////////////////////
interface Herbivore {
	public void eatPlants();
}
interface Omnivore {
	public void eatPlants();
	public void eatMeat();
}

class Bear implements Herbivore, Omnivore {
	public void eatMeat() {
		System.out.println("Eating meat");
	}

	public void eatPlants() {        ///this works fine, both interfaces have same signature
		System.out.println("Eating plants");
	}
}
/////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////			METHOD OVERLOADING 			////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////
interface Herbivore1 {
	public int eatPlants(int quantity);}
interface Omnivore1 {
	public void eatPlants();}
class Bear1 implements Herbivore1, Omnivore1 {
//	class that implements both interfaces must provide
//	implements of both versions of eatPlants(), since they are considered separate methods.
	public int eatPlants(int quantity) {
		System.out.println("Eating plants: " + quantity);
		return quantity;}

	public void eatPlants() {
		System.out.println("Eating plants");
	}
}
////////////////	code doesnТt compile, as the class defines two methods with the same name and
////////////////    input parameters but different return types
interface Herbivore2 {
public int eatPlants();
}
interface Omnivore2 {
public void eatPlants();
}
class Bear2 implements Herbivore2, Omnivore2 {
	public int eatPlants() { // DOES NOT COMPILE
		System.out.println("Eating plants: 10");
		return 10;
	}
	public void eatPlants() { // DOES NOT COMPILE
		System.out.println("Eating plants");
	}
}
//ABSTRACT CLASS also DOES NOT compile because of conflicting methods
abstract class AbstractBear implements Herbivore2, Omnivore2 {}
////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////		Default Interface Methods 		////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
/*The following are the default interface method rules:
		1) A default method may only be declared within an interface and not within a class or
abstract class.
		2) A default method must be marked with the default keyword. If a method is marked as
default, it must provide a method body.
		3) A default method is not assumed to be static, final, or abstract, as it may be used
or overridden by a class that implements the interface.
		4) Like all methods in an interface, a default method is assumed to be public and will not
compile if marked as private or protected.
		5) An interface can redeclare a default method and also make it abstract. */
/*	Default methods CANNOT BE MARKED AS STATIC and require an instance of the class implementing the interface
to be invoked.
 	They also CANNOT BE MARKED AS FINAL or ABSTRACT, because they are
allowed to be overridden in subclasses but are not required to be overridden. */
interface IsWarmBlooded {
	boolean hasScales();

	public default double getTemperature() { //default word MUST be used
	//only public, abstract, default, static and strictfp are permitted
//	Illegal combination of modifiers for the interface method getTemperature; 
//	ONLY ONE of abstract, default, or static permitted
		return 10.0;}
	public default void eatMeat(); // DOES NOT COMPILE
	public int getRequiredFoodAmount() { // DOES NOT COMPILE
						return 13;}
////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////		DEFAULT METHODS and MULTIPLE INHERITANCE		////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
interface Walk {
		public default int getSpeed() {
			return 5;}
	//	public int getSpeed(); // даже если в одном из имплементируемых интерфейсов метод будет абстрактным,
								// а в другом -- дефолтным, класс Cat не будет скомпилирова!!!!!!!!!!
		} 				//	An interface can redeclare a default method and also make it abstract.
interface Run {
		public default int getSpeed() {
			return 7;}}
interface RunFast extends Run {
	public default int getSpeed() { // переопределение дефолтного метода
		return 10;}}
	
}
class Cat implements Walk, Run { // DOES NOT COMPILE, т.к. есть 2 одинаковых дефолтных метода от разных интерфейсов
		public static void main(String[] args) {
			System.out.println(new Cat().getSpeed());
		}}

class  Cat2 implements Walk, Run { // COMPILES
	public int getSpeed() {return 8;} // default метод переопределен на уровне класса, поэтому коллизи€ исчезла
	public static void main(String[] args) {
		System.out.println(new Cat2().getSpeed());
	}
}
////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////		INTERFACE STATIC METHODS 	////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
/*
		1) Like all methods in an interface, a static method is assumed to be public and will not
compile if marked as private or protected.
		2) To reference the static method, a reference to the name of the interface must be used. 
		3) A static method defined in an interface is not inherited in any classes that implement the interface.
		4) Class that implements two interfaces containing static methods
with the same signature will still compile at runtime, because the static methods are not
inherited by the subclass and must be accessed with a reference to the interface name.
		*/
interface Hop {
	static int getJumpHeight() {
		return 8;
	}}
class Bunny implements Hop {
	public void printDetails() {
		System.out.println(getJumpHeight()); //DOES NOT COMPILE
		// не компилируетс€, т.к. STATIC ћ≈“ќƒџ »Ќ“≈–‘≈…—ќ¬ Ќ≈ Ќј—Ћ≈ƒ”ё“—я »ћѕЋ≈ћ≈Ќ“»–”ёў»ћ»  Ћј——јћ»
		System.out.println(Hop.getJumpHeight()); //Ќо такой вызов работает нормально
		
	}}

////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////			INTERFACE VARIABLES 		////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
interface CanDig {
	private int MAXIMUM_DEPTH = 100; // DOES NOT COMPILE
	protected abstract boolean UNDERWATER = false; // DOES NOT COMPILE
	public static String TYPE; // DOES NOT COMPILE
}





