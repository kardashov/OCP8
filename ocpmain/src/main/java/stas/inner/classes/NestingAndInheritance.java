package stas.inner.classes;

public class NestingAndInheritance {
    public static void main(String args[]) {
        B.C obj = new B().new C();
    }
}

class A {
    char c;

    A(char c) {
        this.c = c;
    }
}

class B extends A {
    char c = 'a';

    B() {
        super('b');
    }

    class C extends A {
        char c = 'c';

        C() {
            super('d');
            System.out.println(B.this.c); // 'a'
            System.out.println(C.this.c);// 'c'
            System.out.println(super.c);// 'd'
        }
    }
}
/* Every non-static inner class object has a reference to its outer class object
 which can be accessed by doing OuterClass.this. So the expression B.this.c
 will refer to B's c, whose value is 'a'. Inside a non-static inner class,
 'InnerClass.this' is equivalent to 'this'. so 'C.this.c' refers to C's c
 which is 'c'. The expression super.c will access the variable from A, the
 superclass of C whose value is 'd'.*/