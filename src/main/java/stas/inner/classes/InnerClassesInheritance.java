package stas.inner.classes;

public class InnerClassesInheritance {

	public static void main(String[] args) {

		MyClass 				mc = new MyClass();
		MyClass.InnerNonStatic  c;
		MyClass.InnerStatic 	b;
		MyClass.mynum 			mm;
		
		Child 					f = new Child();
		f.new InnerNonStatic();
		
		Child.InnerNonStatic 	r;//references to inner classes of parent class allowed
		Child.InnerStatic 		df; //references to inner classes of parent class allowed
		Child.mynum             mch;
		
		System.out.println(MyClass.mynum.ssdf);
		System.out.println(MyClass.InnerStatic.age);
	}
}

class MyClass {

	int myIntVar;
	public static enum mynum {sse, ssdf}

	 static class InnerStatic {
		int name;
		static int age;
		
	}

	public class InnerNonStatic {
		String name;
	}
}

class Child extends MyClass {
	InnerStatic is;
}

//class Child2 extends MyClass.InnerNonStatic {} //NOT COMPILES
//No enclosing instance of type MyClass is available due to some intermediate constructor invocation

class MyClass2 extends MyClass  {
	class MyInnerClass2 extends MyClass.InnerNonStatic {}
} //Comiles FINE

//class MyClass3 { //To compile successfully MyClass3 MUST EXTEND MyClass
//	class MyInnerClass3 extends MyClass.InnerNonStatic {} //than this one will have where to live
//}



