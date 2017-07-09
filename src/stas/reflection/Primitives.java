package stas.reflection;

public class Primitives {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Class intClass = int.class;
		System.out.println( "is primitive: " + intClass.isPrimitive());
		// Integer intInstance = intClass.newInstance(); нельзя!
		
	}

}
