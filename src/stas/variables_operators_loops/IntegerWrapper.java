package stas.variables_operators_loops;


public class IntegerWrapper{	
    public static Integer wiggler(Integer x){
       Integer y = x + 10;
       x++; //Integer -- immutable type, изменяется значение только внутри метода.
       System.out.println(x);
       return y;
    }

    public static void main(String[] args){
       Integer dataWrapper = new Integer(5);
       
       Integer value = wiggler(dataWrapper);
       System.out.println(dataWrapper+value);// prints 6 20 
       
       
       
       
       
    }
}
