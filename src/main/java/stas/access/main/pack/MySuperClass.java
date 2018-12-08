package stas.access.main.pack;

public class MySuperClass {
// 			https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html
	/*
						ACCESS LEVELS
	==============================================					
	Modifier	Class	Package	 Subclass	World
	==============================================
	public		  Y		   Y		Y		 Y
	protected	  Y		   Y		Y		 N
	no modifier	  Y		   Y		N		 N
	private		  Y		   N		N		 N	
*/
//	lesson 136  and 169
	public 		String varPublic = "varPublic of MySuperClass";
	protected 	String varProtected = "varProtected of MySuperClass";
				String varPackage = "varPackage of MySuperClass"; //package
	private 	String varPrivate = "varPrivate of MySuperClass";

	
	public 		void methodPublic() {System.out.println("inside MySuperClass.methodPublic method ");	};
	
	protected 	void methodProtected() {System.out.println("inside MySuperClass.methodProtected method ");	};
	
				void methodPackage() {System.out.println("inside MySuperClass.methodPackage method ");	}; //package

	private 	void methodPrivate() {System.out.println("inside MySuperClass.methodPrivate method ");	};
	

	
	
	public static void main(String[] args) {
		MySuperClass f = new MySuperClass();
		
		System.out.println(f.varPublic);
		System.out.println(f.varPackage);
		System.out.println(f.varProtected);
		System.out.println(f.varPrivate);
		
		f.methodPublic();
		f.methodPackage();
		f.methodProtected();
		f.methodPrivate();

		
		
	}

}
