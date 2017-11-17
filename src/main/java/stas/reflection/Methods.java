package stas.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Methods {

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {

		Class<String> stringclass = String.class;
		Method[] methods = stringclass.getMethods();
		Method methodIndexOf = stringclass.getMethod("indexOf", String.class);
		// All methods for the String class
		for (Method method : methods) {
			System.out.println("****************************************************");
			System.out.println("name: " + method.getName());
			System.out.println("defaultValue: " + method.getDefaultValue());
			System.out.println("generic return type: " + method.getGenericReturnType());
			System.out.println("return type: " + method.getReturnType());
			System.out.println("modifiers: " + method.getModifiers());

			// Parameters
			Parameter[] parameters = method.getParameters();
			System.out.println(parameters.length + " parameters:");
			// also method.getParameterCount() is possible
			for (Parameter parameter : parameters) {
				System.out.println("parameter name: " + parameter.getName());
				System.out.println("parameter type: " + parameter.getType());
			}
			Class<?>[] parameterTypes = method.getParameterTypes();
			System.out.println(parameterTypes.length + " parameters:");
			for (Class<?> parameterType : parameterTypes) {
				System.out.println("parameter type name: " + parameterType.getName());
			}

			// Exceptions
			Class<?>[] exceptionTypes = method.getExceptionTypes();
			System.out.println(exceptionTypes.length + " exception types: ");
			for (Class<?> exceptionType : exceptionTypes) {
				System.out.println("exception name " + exceptionType.getName());
			}
			System.out.println("is accesible: " + method.isAccessible());
			System.out.println("is varArgs: " + method.isVarArgs());
		}
		
		String stringer = "this is a String called stringer";
		Object indexOf = methodIndexOf.invoke(stringer, "called");
		System.out.println(indexOf);
	}

}
