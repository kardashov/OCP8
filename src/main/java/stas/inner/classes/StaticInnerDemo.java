package stas.inner.classes;
public class StaticInnerDemo {
	
	static class Nested {//============begin of nested static class===============
		private int price = 6;
		static int i = 9;
		static String getInnerStaticString() {
			return "return of getInnerStaticString";
		}
	}//================================end of nested static class=================

	public static void main(String[] args) {
		Nested nested = new Nested(); // create new instance of nested static class
		System.out.println(nested.price); //refer to private INSTANCE VARIABLE of nested static class
		
		System.out.println(Nested.i);//refer to STATIC VARIABLE of nested static class
//		System.out.println(this.i);//does not compile
//		System.out.println(i);     //does not compile
		Nested.getInnerStaticString();// works fine
	}
}
/*
Java treats the static nested class as if it were a namespace.
You can use a static import: */
//import static bird.Toucan.Beak;
class My {
	void SomeMethod(){
		System.out.println(StaticInnerDemo.Nested.i);//access inner static class from outer classes
		StaticInnerDemo.Nested.getInnerStaticString();
	}
}
