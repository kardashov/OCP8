package stas.methods.polymorphism;

/////////////////////////////////////////////////////////////////////////////////////////
///////////////////////				VIRTUAL METHODS			 	////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////
//		If you call a method on an object that overrides a method, you get the overridden method,
//		even if the call to the method is on a parent reference or within the parent class.
class Bird1 {
	public String getName() {
		return "Unknown";	}

	public void displayInformation() {
		System.out.println("The bird name is: " + getName());	}
}

public class PolymorphismDemo extends Bird1 {
	public String getName() {
		return "Peacock";	}

	public static void main(String[] args) {
		Bird1 bird = new PolymorphismDemo();
		bird.displayInformation(); // The bird name is: Peacock

//		The method getName() is overridden in the child class Peacock. More importantly, though, the value of
//		the getName() method at runtime in the displayInformation() method is replaced with
//		the value of the implementation in the subclass Peacock.
		
//		In other words, even though the parent class Bird defines its own version of getName()
//		and doesn’t know anything about the Peacock class during compile-time, at runtime the
//		instance uses the overridden version of the method, as defined on the instance of the object.
//		We emphasize this point by using a reference to the Bird class in the main() method,
//		although the result would have been the same if a reference to Peacock was used.
		
		bird = new Bird1();
		bird.displayInformation(); // The bird name is: Peacock
	}
}
