package stas.classloader;

public class ClassLoaderReloadTest {

	public static void main(String[] args)
			throws ClassNotFoundException, IllegalAccessException, InstantiationException {

		
		// http://tutorials.jenkov.com/java-reflection/dynamic-class-loading-reloading.html

		// If the myClassReloadingFactory object factory reloads the MyObject
		// class using a different class loader than the class the above code
		// resides in, you cannot cast the instance of the reloaded MyObject
		// class to the MyObject type of the object variable. Since the two
		// MyObject classes were loaded with different class loaders, the are
		// regarded as different classes, even if they have the same fully
		// qualified class name. Trying to cast an object of the one class to a
		// reference of the other will result in a ClassCastException.
		//
		// It is possible to work around this limitation but you will have to
		// change your code in either of two ways:
		//
		// Use an interface as the variable type, and just reload the
		// implementing class.
		// Use a superclass as the variable type, and just reload a subclass.
		
		System.out.println("Begin...");
		
		ClassLoader parentClassLoader = MyClassLoader.class.getClassLoader();
		MyClassLoader classLoader = new MyClassLoader(parentClassLoader);
		Class myObjectClass = classLoader.loadClass("stas.classloader.MyObject");

		
		AnInterface2 object1 = (AnInterface2) myObjectClass.newInstance();

		MyObjectSuperClass object2 = (MyObjectSuperClass) myObjectClass.newInstance();

		// create new class loader so classes can be reloaded.
		classLoader = new MyClassLoader(parentClassLoader);
		myObjectClass = classLoader.loadClass("stas.classloader.MyObject");

		object1 = (AnInterface2) myObjectClass.newInstance();
		object2 = (MyObjectSuperClass) myObjectClass.newInstance();
		System.out.println("...Done");
	}
}