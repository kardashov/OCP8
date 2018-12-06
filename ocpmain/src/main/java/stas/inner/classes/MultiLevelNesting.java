package stas.inner.classes;

public class MultiLevelNesting {
    private int x = 10;

    class Level2 {
        private int x = 20;
        static final int dd = 555;

        class Level3 {
            private int x = 30;
            final static int dd = 333; //static fields MUST be final

            //			static int sss() {}; 	 // COMPILE ERROR   can't define static methods
            public void allTheX() {
                System.out.println("=============allTheX start=============");
                System.out.println(x);                          // 30   same as this.x
                System.out.println(this.x);                  // 30   accessing instance variables
                System.out.println(Level2.this.x);              // 20	  on different levels
                System.out.println(MultiLevelNesting.this.x); // 10
                System.out.println(dd);       //access static variable
                System.out.println(Level3.dd);//access static variable
                System.out.println(Level2.dd);//access static variable
                System.out.println("=============allTheX end===============");
            }
        }
    }

    public static void main(String[] args) {
        MultiLevelNesting a = new MultiLevelNesting();

        MultiLevelNesting.Level2 b = a.new Level2();
        Level2 b1 = a.new Level2();//COMPILES

        MultiLevelNesting.Level2.Level3 c = b.new Level3();
        Level2.Level3 c1 = b.new Level3();
//						  		 Level3 c2 = b.new Level3();// Level3 cannot be resolved to a type
        c.allTheX();
        System.out.println(c.dd);
    }
}
