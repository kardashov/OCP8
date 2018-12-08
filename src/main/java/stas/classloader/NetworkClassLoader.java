package stas.classloader;

class NetworkClassLoader extends ClassLoader {
	String host;
	int port;

	public NetworkClassLoader(String host, int port) {
		this.host = host;
		this.port = port;

	}

	public Class findClass(String className) {
		byte[] bytes = loadClassData(className);
		return defineClass(className, bytes, 0, bytes.length);
	}

	private byte[] loadClassData(String className) {
		byte[] result = null;
		// open connection, load the class data
		return result;
	}
}
//http://tutorials.jenkov.com/java-reflection/dynamic-class-loading-reloading.html


// получение системного класс-лоадера
// ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

// A programmer can also get the specific class loader used for loading a given
// class or instance. If nothing else is specified or
// configured, the default one is used. For example:
// ClassLoader classClassLoader = ReflectableClass.class.getClassLoader();

// Using a class loader we can load classes at runtime passing as parameter the
// qualified name of a class, this name can be generated
// dynamically:
// Class<?> reflectableClassInstanceLoaded = systemClassLoader.loadClass(
// "com.danibuiza.javacodegeeks.reflection.ReflectableClass" );

// Another possibility is to to this using Class.forName() method and specify
// the class loader that we want to use as parameter:
// Class<?> reflectableClassInstanceForName = Class.forName(
// "com.danibuiza.javacodegeeks.reflection.ReflectableClass", true, -
// systemClassLoader );

// загрузка класса с помощью класслоадера может выгл€деть так :
// try {
// ClassLoader loader =
// new NetworkClassLoader(host, port);
// Object main = loader.loadClass(
// "Main").newInstance();
// } catch(ClassNotFoundException e) {
// e.printStackTrace();
// } catch(InstantiationException e) {
// e.printStackTrace();
// } catch(IllegalAccessException e) {
// e.printStackTrace();
// }
// ≈сли такой класс не будет найден, будет брошено исключение
// ClassNotFoundException, если класс будет найден, но произойдет кака€-либо
// ошибка при создании объекта этого класса Ц будет брошено исключение
// InstantiationException, и, наконец, если у вызывающего потока не имеетс€
// соответствующих прав дл€ создани€ экземпл€ров этого класса (что провер€етс€
// менеджером безопасности), будет брошено исключение IllegalAccessException.
