package stas.access.main.pack;

import stas.access.other.pack.ShadowingDemoOtherPack;

public class ShadowingDemo extends MySuperClass {
    public String varPublic = "varPublic of ShadowingDemo";
    protected String varProtected = "varProtected of ShadowingDemo";
    String varPackage = "varPackage of ShadowingDemo";
    private String varPrivate = "varPrivate of ShadowingDemo";  //can be ANY type

    public void methodPublic() {
        System.out.println("inside ShadowingDemo.methodPublic method ");
    }

    ;

    protected void methodProtected() {
        System.out.println("inside ShadowingDemo.methodProtected method ");
    }

    ;

    void methodPackage() {
        System.out.println("inside ShadowingDemo.methodPackage method ");
    }

    ; //package

    private void methodPrivate() {
        System.out.println("inside ShadowingDemo.methodPrivate method ");
    }

    ;

    void myMethod() {

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
        System.out.println(super.varPackage);
//		System.out.println(super.varPrivate); superclass' private variables not visible from here

        System.out.println("===========================================================");
        System.out.println("Accessing SUPER.methods in ShadowingDemo inside myMethod...");
        super.methodPublic();
        super.methodProtected();
        super.methodPackage();
//		super.methodPrivate(); 	superclass' private methods not visible from here

        System.out.println("===========================================================");
        System.out.println("Accessing ShadowingDemoOtherPack.methodPackage() via MySuperClass reference \n" +
                "from ShadowingDemo inside myMethod...");

        MySuperClass s = new ShadowingDemoOtherPack();
        s.methodProtected();//inside ShadowingDemo.methodProtected method
        s.methodPackage();  //inside MySuperClass.methodPackage method
    }


    public static void main(String[] args) {
        MySuperClass f = new MySuperClass();
        System.out.println("===========================================================");
        System.out.println("Accessing MySuperClass.variables via reference MySuperClass");
//		System.out.println(f.varPrivate); // private members
        System.out.println(f.varPackage);
        System.out.println(f.varProtected);
        System.out.println(f.varPublic);

        System.out.println("====================================================");
        System.out.println("Accessing MySuperClass.methods via MySuperClass ...");
//		f.methodPrivate(); // private members not accessible
        f.methodPackage();
        f.methodProtected();
        f.methodPublic();


        ShadowingDemo shadow = new ShadowingDemo();
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
//		System.out.println(f.varPrivate); // private members
        System.out.println(f.varPackage);
        System.out.println(f.varProtected);
        System.out.println(f.varPublic);

        System.out.println("====================================================");
        System.out.println("Accessing ShadowingDemo.methods via MySuperClass!!!!");
//		f.methodPrivate(); // private members not accessible here
        f.methodPackage();
        f.methodProtected();
        f.methodPublic();

        System.out.println("===========================================");
        System.out.println("Calling myMethod...");
        shadow.myMethod();
    }
}
