package stas.access.other.pack;

import stas.access.main.pack.MySuperClass;

public class ShadowingDemoOtherPack extends MySuperClass {
	public 		String varPublic 	= "varPublic of ShadowingDemo";   //can be ANY type, shadows variable from SuperClass
	protected 	String varProtected = "varProtected of ShadowingDemo";//can be ANY type, shadows variable from SuperClass
				String varPackage 	= "varPackage of ShadowingDemo"; //can be ANY type, shadows variable from SuperClass
	private 	String varPrivate 	= "varPrivate of ShadowingDemo";  //can be ANY type 
	
	public 		void methodPublic() {System.out.println("inside ShadowingDemo.methodPublic method ");	};
	
	protected 	void methodProtected() {System.out.println("inside ShadowingDemo.methodProtected method ");	};
	
				void methodPackage() {System.out.println("inside ShadowingDemo.methodPackage method ");	}; //package
//				The method ShadowingDemoOtherPack.methodPackage() does not override the inherited 
//				method from MySuperClass since it is private to a different package
	private 	void methodPrivate() {System.out.println("inside ShadowingDemo.methodPrivate method ");	};
	
	void myMethod(){
	
		System.out.println("============================================================");
		System.out.println("Accessing THIS.variables in ShadowingDemo inside myMethod...");
		System.out.println(this.varPublic);
		System.out.println(this.varProtected);
		System.out.println(this.varPackage);
		System.out.println(this.varPrivate);

		System.out.println("==========================================================");
		System.out.println("Accessing THIS.methods in ShadowingDemo inside myMethod...");
		this.methodPublic();
		this.methodProtected();
		this.methodPackage();
		this.methodPrivate();

		System.out.println("=============================================================");
		System.out.println("Accessing SUPER.variables in ShadowingDemo inside myMethod...");
		System.out.println(super.varPublic);
		System.out.println(super.varProtected);
//		System.out.println(super.varPackage); superclass' package variables not visible from here
//		System.out.println(super.varPrivate); superclass' private variables not visible from here
		
		System.out.println("===========================================================");
		System.out.println("Accessing SUPER.methods in ShadowingDemo inside myMethod...");
		super.methodPublic();
		super.methodProtected();
//		super.methodPackage(); //superclass' package methods not visible from here
//		super.methodPrivate(); 	superclass' private methods not visible from here
	}
	
	
	public static void main(String[] args) {
		MySuperClass f = new MySuperClass();
		System.out.println("===========================================================");
		System.out.println("Accessing MySuperClass.variables via reference MySuperClass");		
//		System.out.println(f.varPrivate); // private members
//		System.out.println(f.varPackage); //not visible here
//		System.out.println(f.varProtected); //not visible here
		System.out.println(f.varPublic);
		
		System.out.println("====================================================");
		System.out.println("Accessing MySuperClass.methods via MySuperClass ...");		
//		f.methodPrivate(); // private members not accessible 
//		f.methodPackage();	//not visible here
//		f.methodProtected();//not visible here	
		f.methodPublic();
		
		ShadowingDemoOtherPack shadow = new ShadowingDemoOtherPack();
		System.out.println("=============================================================");
		System.out.println("Accessing ShadowingDemo.variables via ShadowingDemo reference");		
		System.out.println(shadow.varPrivate);
		System.out.println(shadow.varPackage);
		System.out.println(shadow.varProtected);
		System.out.println(shadow.varPublic);
		
		System.out.println("===========================================================");
		System.out.println("Accessing ShadowingDemo.methods via ShadowingDemo reference");		
		shadow.methodPrivate();
		shadow.methodPackage();	
		shadow.methodProtected();	
		shadow.methodPublic();
		
		f = shadow;
		System.out.println("==================================================================");
		System.out.println("Accessing ShadowingDemo.variables via reference MySuperClass!!!!!!");		
//		System.out.println(f.varPrivate);//superclass' private members not accessible by reference 
//		System.out.println(f.varPackage);//superclass' package members not accessible by reference (subclass from other package)
//		System.out.println(f.varProtected);//superclass' protected members not accessible by reference (subclass from other package)	
		System.out.println(f.varPublic);
		
		System.out.println("====================================================");
		System.out.println("Accessing ShadowingDemo.methods via MySuperClass!!!!");		
//		f.methodPrivate(); 	//superclass' private members not accessible
//		f.methodPackage();	//superclass' package members not accessible by reference (subclass from other package)
//		f.methodProtected();//superclass' protected members not accessible by reference (subclass from other package)	 
		f.methodPublic();

		System.out.println("===========================================");
		System.out.println("Calling myMethod...");
		shadow.myMethod();
	}
}
