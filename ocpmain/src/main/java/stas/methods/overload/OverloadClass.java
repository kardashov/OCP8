package stas.methods.overload;


public class OverloadClass {
    public void fly(int numMiles) {
    }

    public void fly(short numFeet) {
    }

    public boolean fly() {
        return false;
    }

    void fly(int numMiles, short numFeet) {
    }

    public void fly(short numFeet, int numMiles) throws Exception {
    }
//	We can 	have a different type, more types, or the same types in a different order.
//	Also notice that the access modifier and exception list are irrelevant to overloading.

    public void fly(int numMiles) {
    }

    public int fly(int numMiles) {
    } // DOES NOT COMPILE
//	This method doesn�t compile because it only differs from the original by return type.


    public void fly(int numMiles) {
    }

    public static void fly(int numMiles) {
    } // DOES NOT COMPILE
//	Again, the parameter list is the same. The only difference is that one is an instance
//	method and one is a static method.

    //	===========================================================================
//	==========================Overloading and Varargs==========================
//	===========================================================================
    public void fly(int[] lengths) {
    }

    public void fly(int... lengths) {
    } // DOES NOT COMPILE
//	!!!!!!!!Java treats varargs as if they were an array!!!!!!!!!!!
//	This means	that the method signature is the same for both methods.

    //	you can call either method by passing an array:
    fly(new int[] {
        1, 2, 3
    });

    //	But only  the varargs version could be called with stand-alone parameters:
    fly(1,2,3);

    //	===========================================================================
//	==========================Overloading and Varargs==========================
//	===========================================================================
    public void fly(int numMiles) {
    }

    public void fly(Integer numMiles) {
    }  // compiles OK
//	but at runtime it will call more specific version 
//	fly(3); will call 1st one

    //	===========================================================================
//	==========================Reference Types==================================
//	===========================================================================
    public void flyRef(String s) {
        System.out.print("string ");
    }

    public void flyRef(Object o) {
        System.out.print("object ");
    }

    flyRef("test"); // prints string

    flyRef(56);//prints object

    //	===========================================================================
//	==========================Primitive Types==================================
//	===========================================================================	
    public void fly(int i) {
        System.out.print("int ");
    }

    public void fly(long l) {
        System.out.print("long ");
    }

    fly(123);  //prints int

    fly(123L); //prints long
//	If we comment out the overloaded method with the int parameter list, the output becomes long long. 
//	Java has no problem	calling a larger primitive. However, it will not do so unless a better match is not found.
//	Note that Java can only accept wider types. If you want to pass
//	a long to a method taking an int parameter, you have to add a cast to explicitly say narrowing	is okay.

//	===========================================================================
//	==========================OVERALL RULES====================================
//	===========================================================================		
//	Rule 						Example of what will be chosen for glide(1,2)
//	Exact match by type 		public String glide(int i, int j) {}
//	Larger primitive type 		public String glide(long i, long j) {}
//	Autoboxed type 				public String glide(Integer i, Integer j) {}
//	Varargs 					public String glide(int... nums) {}


    public static String glide(String s) {
        return "1";
    }

    public static String glide(String... s) {
        return "2";
    }

    public static String glide(Object o) {
        return "3";
    }

    public static String glide(String s, String t) {
        return "4";
    }
		
		System.out.print(

    glide("a"));            // 1
		System.out.print(

    glide("a","b"));        // 4
		System.out.print(

    glide("a","b","c")); // 2

    //		===========================================================================
//		==========================TOO MANY CONVERSIONS!!!!!!!!!!===================
//		===========================================================================		����� �����!!!!!!!!
    public static void play(Long l) {
    }

    public static void play(Long... l) {
    }

    play(4); // DOES NOT COMPILE

    play(4L); // calls the Long version
//		Java is happy to convert the int 4 to a long 4 or an Integer 4.
//		It cannot handle converting in two steps to a long and then to a Long. If we had public
//		static void play(Object o) { }, it would match because only one conversion would be
//		necessary: from int to Integer.


    public static void main(String[] args) {

    }

}
