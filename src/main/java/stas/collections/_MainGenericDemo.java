package stas.collections;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class _MainGenericDemo {
	
	public static void main(String[] args) {
		
		List<?> l = null;
		List<String> c = null;

		l = c;
		// c = l; // DOES NOT COMPILE
		c = (List<String>) l; // explicit cast is needed

		MyGeneric my = new MyGeneric();
		
		MyGeneric.sink("sss");
		MyGeneric.sink(33445);
		
		MyGeneric.<String>sink("sss");
//		MyGeneric.<String>sink(33445);//Doesn't compile
		
		MyGeneric.identity(22333);
//		MyGeneric.identity("From Generic static Method identity()"));//DOESNT COMPILE. String doesnt extend Number
		
		
		my.<String>myGenericInstanceMethod("sdf");//explicit type mentioned
		my.myGenericInstanceMethod("seeerr"); //TYPE inference
		my.myGenericInstanceMethod(334);
//		my.<String>myGenericInstanceMethod(4564);//DOES NOT COMPILE
		
//		my.acceptList(new ArrayList<>());
		my.acceptList(new ArrayList<Byte>());
		my.acceptList(new ArrayList<Number>());
		
//		my.acceptList(new ArrayList<String>()); //DOES NOT COMPILE
		
		MyGeneric2<Number> my2 = new MyGeneric2<>();//Doesn't COMPILE
//		MyGeneric2<String> my2 = new MyGeneric2<>();//Doesn't COMPILE
		my2.setC( 334.3);
		my2.getC();
		System.out.println("==============Wildcards===================================");
		
//		ArrayList<Number> list = new ArrayList<Integer>(); // DOES NOT COMPILE 
		List<? extends Number> list = new ArrayList<Integer>();
//			list.add(2); //DOES NOT COMPILE
		
		List<?> list3 = new ArrayList<>();
//			list3.add(2); //DOES NOT COMPILE
		
		List<Number> list2 = new ArrayList<>();
			list2.add(2);//compiles fine
			
		List<? extends Bird> birds = new ArrayList<Bird>();
//			birds.add(new Sparrow());// DOES NOT COMPILE
//			birds.add(new Bird());// DOES NOT COMPILE
		
		
		List<? super IOException> exceptions = new ArrayList<Object>();
								  exceptions = new ArrayList<IOException>();
								  exceptions = new ArrayList<Exception>();
								  
//		exceptions.add(new Exception()); //// DOES NOT COMPILE
		exceptions.add(new IOException());
		exceptions.add(new FileNotFoundException());
		
		Object t = exceptions.get(1);
//		Exception cc =  exceptions.get(1);//Does not compile without explicit cast
		
		
		List<Set<?>> v = new ArrayList<>();
		Set<String> set = new TreeSet<>();
		v.add(set);

		
	}
	//=======================END OF MAIN METHOD================================================
	
	private void anyFlyer(List<Flyer> flyer) { /*getters and setters of List allowed for flyer*/}
	
//	private void groupOfFlyers(List<? extends Flyer> flyer) {/*only getters allowed for flyer*/ }
	//Cannot have both of this method in a single class because
	//Erasure of method groupOfFlyers(List<? extends Flyer>) is the same as another method in type
	
	private void groupOfFlyers(List<? super Flyer> flyer) { /*only setters allowed for flyer, 
													getters return Object thus require explicit cast*/ }
}
///========================END OF _MainGenericDemo class================================================

class Sparrow extends Bird {}
class Bird {}
interface Flyer { void fly(); }
class HangGlider implements Flyer { public void fly() {} }
class Goose 	 implements Flyer { public void fly() {} }
//=================================================================================
class MyGeneric {
	public static <T> void sink(T t) {
		T c = t;
	}

	public static <T extends Number> T identity(T t) {
		return t;
	}

	public <T> void myGenericInstanceMethod(T t) {
		System.out.println(t.toString());
	}
//	public static T noGood(T t) {return t;} // DOES NOT COMPILE
	
	public static long total(List<? extends Number> list) {
		long count = 0;
		for (Number number : list)
			count += number.longValue();
		return count;
	}
	
	public void acceptList(List<? extends Number> f) {
			for (Number number : f) {
				System.out.println(number);
			}
			
//			f.get(0); //COMPILES
//			f.addAll(333);//DOES NOT COMPILE
//			f.add(null); //COMPILES
		}
}
//==================================================================================================
class MyGeneric2<T extends Number> {
	T c;
	public T getC() {
		return c;}
	public void setC(T c) {
		this.c = c;	}
}


////=================================================================================================
////=========================================Using Legacy Code=======================================
////=================================================================================================
class Dragon {}
class Unicorn {}
class LegacyDragons {
	public static void main(String[] args) {
		List unicorns = new ArrayList();
		unicorns.add(new Dragon());
		unicorns.add(new Unicorn());
		printDragons(unicorns);
	}

	private static void printDragons(List<Dragon> dragons) {
		for (Dragon dragon : dragons) {						// ClassCastException 
			System.out.println(dragon);
		}
	}
}
///==================================================================================================
class LegacyUnicorns {
	public static void main(String[] args) {
		java.util.List<Unicorn> unicorns = new java.util.ArrayList<>();
		addUnicorn(unicorns);
		Unicorn unicorn = unicorns.get(0);					// ClassCastException
	}

	private static void addUnicorn(List unicorn) {
		unicorn.add(new Dragon());
	}
}
//===================================================================================================
class LegacyAutoboxing {
	public static void main(String[] args) {
		java.util.List numbers = new java.util.ArrayList();
		numbers.add(5);
		//int result = numbers.get(0); // DOES NOT COMPILE
	}
}
//====================================================================================================


