package stas.inner.classes;

public class Outer {
    private String greeting = "Hi";

    /////=================================================Member inner class start==============
    protected class Inner {
        public int repeat = 3;
        private int private_int = 0;

        public void go() {
            for (int i = 0; i < repeat; i++)
                System.out.println(greeting); // it can use the instance variables
            // declared in the outer class
        }
    }

    ////====================================================Member inner class end===============
    public void callInner() {
        Inner inner = new Inner();
        inner.go();
        int f = inner.private_int; //private inner fields visible to Outer class
    }


    public static void main(String[] args) {
        Outer2 outer = new Outer2();  // create instance of Outer class
        outer.callInner();

        Inner inner = outer.new Inner();// we need instance of Outer class to create Inner
        inner.go();


    }
}
