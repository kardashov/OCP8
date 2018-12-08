package stas.reflection;

import java.lang.reflect.Field;

class A {
	private String field = "I'm private field";
	
}

public class AccessPrivateField {
	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		A a = new A();
		
		Field f = a.getClass().getDeclaredField("field");
		f.setAccessible(true);
		String fieldValue = (String) f.get(a);

		System.out.println(fieldValue);
	}
}
