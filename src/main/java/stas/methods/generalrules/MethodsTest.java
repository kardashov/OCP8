package stas.methods.generalrules;

public class MethodsTest {

	public void walk1() {}
//	default void walk2() {} // DOES NOT COMPILE
//	void public walk3() {} // DOES NOT COMPILE
	void walk4() {}
	
//	static Covered later in this chapter. Used for class methods.
//	abstract Covered in Chapter 5. Used when not providing a method body.
//	final Covered in Chapter 5. Used when a method is not allowed to be overridden by a	subclass.
//	synchronized On the OCP but not the OCA exam.
//	native Not on the OCA or OCP exam. Used when interacting with code written in other language such as C++.
//	strictfp Not on the OCA or OCP exam. Used for making fl oating-point calculations portable.
	
	public void walk8() {}
	public final void walk2() {}
	public static final void walk3() {}
	public final static void walk9() {}
//	public modifier void walk5() {} // DOES NOT COMPILE
//	public void final walk6() {} // DOES NOT COMPILE
	final static public void walk7() {}
	final public static void walk10() {}
	
	
	
//	Methods	with a return type other than void are required to have a return statement inside the
//	method body. This return statement must include the primitive or object to be returned.
//	Methods that have a return type of void are permitted to have a return statement with no
//	value returned or omit the return statement entirely.
	public void walk11() { }
	public void walk12() { return; }
	public String walk13() { return ""; }
	public String walk14() { } // DOES NOT COMPILE
	public walk15() { } // DOES NOT COMPILE
	String walk16(int a) { if (a == 4) return ""; } // DOES NOT COMPILE
	String walk17(int a) { if (4 > 2) return "";  } // DOES NOT COMPILE

//	When returning a value, it needs to be assignable to the return type. Imagine there is a
//			local variable of that type to which it is assigned before being returned.
	int integer() {	return 9;}
	int longMethod() {return 9L;} // DOES NOT COMPILE
	
		
	//Parameter list
	public void walk18() { }
	public void walk21 { } // DOES NOT COMPILE
	public void walk22(int a) { }
	public void walk23(int a; int b) { } // DOES NOT COMPILE
	public void walk24(int a, int b) { }
	
//	Optional Exception list 
	public void zeroExceptions() { }
	public void oneException() throws IllegalArgumentException { }
	public void twoExceptions() throws IllegalArgumentException, InterruptedException { }

//	Method body
	public void walk25() { }
	public void walk26; // DOES NOT COMPILE  no body method
	public void walk27(int a) { int name = 5; }
	
//	Working with Varargs
	public void walk1(int... nums) { }
	public void walk2(int start, int... nums) { }
	public void walk3(int... nums, int start) { } // DOES NOT COMPILE
	public void walk4(int... start, int... nums) { } // DOES NOT COMPILE
	
	
//	=======================METHOD ACCESS MODIFIERS====================================
//	private: Only accessible within the same class
//	default (package private) access: private and other classes in the same package
//	protected: default access and child classes
//	public: protected and classes in the other packages
	
	
	public static void main(String[] args) {

	}

}
