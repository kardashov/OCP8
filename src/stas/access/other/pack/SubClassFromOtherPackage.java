package stas.access.other.pack;

import stas.access.main.pack.MySuperClass;

public class SubClassFromOtherPackage extends MySuperClass {

	 
	public void myMethod(){
		
		System.out.println(this.varPrivate); 
		System.out.println(this.varPackage);
		System.out.println(this.varProtected);
		System.out.println(this.varPublic);		
		
		this.methodPrivate();
		this.methodPackage();
		this.methodProtected();
		this.methodPublic();
	}
	
	
	public static void main(String[] args) {

	MySuperClass f = new MySuperClass();
	
	System.out.println(f.varPrivate);
	System.out.println(f.varPackage);
	System.out.println(f.varProtected);
	System.out.println(f.varPublic);
	
	f.methodPrivate();
	f.methodPackage();	
	f.methodProtected();	
	f.methodPublic();
	
	// о как!!!
	SubClassFromOtherPackage myVar = new SubClassFromOtherPackage();
	
	System.out.println(myVar.varPrivate);
	System.out.println(myVar.varPackage);
	System.out.println(myVar.varProtected);
	System.out.println(myVar.varPublic);
	
	myVar.methodPrivate();
	myVar.methodPackage();	
	myVar.methodProtected();	
	myVar.methodPublic();

	}
}
