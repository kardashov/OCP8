package stas.interfaces;
/*
super.methodName is a valid way to invoke a super class's method from anywhere within a subclass's method. 
But it works only for classes. To invoke an interface's default method, you need to use the name of that 
interface as well. Like this: Account.super.getId();  A class (or an interface) can 
invoke a default method of an interface that is explicitly mentioned in 
the class's implements clause (or the interface's extends clause) by using 
the same syntax i.e. <InterfaceName>.super.<methodName>.  However, this technique 
cannot be used to invoke a default method provided by
		an interface that is not directly implemented (or extended) by the caller. Here is an example: */
interface A {
	default void hello() {
	}
}

interface B extends A {
	default void hello() {
		A.super.hello();	//This is valid.
		super.hello();		//This is NOT valid.      
		
   }
}

public class DirectDefaultMethodInvokation implements B {
	public void hello() {
		B.super.hello(); 	// This is valid.
		
		super.hello();		// This is NOT valid.       
		A.super.hello(); 	// This is NOT valid because TestClass does not
							// implement A directly.  
		//	Illegal reference to super type A, cannot bypass the more specific direct super type 
		//	 stas.interfaces.B    
		  
		  }
}