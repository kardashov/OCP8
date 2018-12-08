package stas.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyDynamicProxyInvokationHandlerImpl implements InvocationHandler {
	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
		System.out.println("using proxy " + proxy.getClass().getName());
		System.out.println("method " + method.getName() + " from interface " + method.getDeclaringClass().getName());
		// we can check dynamically the interface and load the implementation
		// that we want
		// if( method.getDeclaringClass().getName().equals(
		// "com.danibuiza.javacodegeeks.reflection.InformationInterface" ) )
		// {
		// InformationClass informationImpl =
		// InformationClass.class.newInstance();
//		return method.invoke(proxy, arguments);
		 
//		 return "SSSSS";
		// }
		 return null;
		
//		return;

		
	}
}