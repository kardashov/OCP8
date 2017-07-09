package stas.lambda;

public class RunnableTest {
	
	public static void main(String[] args) {
    
    System.out.println("=== RunnableTest ===");
     
     // Anonymous Runnable
     Runnable r1 = new Runnable(){
       
       @Override
       public void run(){
         System.out.println("Hello world one!");
       }
     };
     
     // Lambda Runnable
     Runnable r2 = () -> System.out.println("Hello world two!");
     // Run em!
     r1.run();
     r2.run();    
     
    
     
     
     
     (String a, String b) -> { return a.equals(b); }
     (Animal a) -> { return a.canHop(); } //базова€ форма л€мбды
//     return и ; об€зательны, т.к. есть {}
     
     	a -> a.canHop()  // если параметр один и без типа, то () можно опустить.
     
     
    print(() -> true); // 0 parameters  return необ€зателен, т.к. нет {}
    print(a -> a.startsWith("test")); // 1 parameter
    print((String a) -> a.startsWith("test")); // 1 parameter
    print((a, b) -> a.startsWith("test")); // 2 parameters
    print((String a, String b) -> a.startsWith("test")); // 2 parameters
    
    
    print(a, b -> a.startsWith("test")); // DOES NOT COMPILE
    print(a -> { a.startsWith("test"); }); // DOES NOT COMPILE
    print(a -> { return a.startsWith("test") }); // DOES NOT COMPILE
//    The first line needs parentheses around the parameter list. Remember that the parentheses
//    are only optional when there is one parameter and it doesnТt have a type declared. The
//    second line is missing the return keyword. The last line is missing the semicolon.
    
    
//////////////////////////////////////////////////////////////////////////////////
//////////////////////		What Variables Can My Lambda Access?   ///////////////
//////////////////////////////////////////////////////////////////////////////////
    boolean wantWhetherCanHop = true;
    print(animals, a -> a.canHop() == wantWhetherCanHop);
//   		 But they cannot access all variables: 
//    1) Instance and static variables are okay;
//    2) Method parameters and local variables are fine IF THEY ARE NOT ASSIGNED NEW VALUES.  
     
//    WeТve been defining an argument list in our lambda expressions.
//    Since Java doesnТt allow us to redeclare a local variable, the
//    following is an issue:
    (a, b) -> { int a = 0; return 5;} // DOES NOT COMPILE   We tried to redeclare a, which is not allowed.
//    We tried to redeclare a, which is not allowed. By contrast, the following line is okay
//    because it uses a different variable name:
    (a, b) -> { int c = 0; return 5;}    
    
    List<String> bunnies = new ArrayList<>();
    bunnies.add("long ear");
    bunnies.add("floppy");
    bunnies.add("hoppy");
    System.out.println(bunnies); // [long ear, floppy, hoppy]
    bunnies.removeIf(s -> s.charAt(0) != 'h');
    System.out.println(bunnies); // [hoppy]

    
     
   }
}

