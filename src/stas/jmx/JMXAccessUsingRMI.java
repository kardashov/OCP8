package stas.jmx;

import javax.management.*;
import java.lang.management.*;
import javax.management.remote.*;

//instructions on usage
//http://java-course.ru/articles/jmx/

//1. download HtmlAdaptorServer 
//http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-java-plat-419418.html#7657-jmx-1.2.1-oth-JPR

//2. install to local maven repo
//mvn install:install-file -Dfile=jmxtools.jar -DgroupId=com.sun.jdmk -DartifactId=jmxtools -Dversion=1.2.1

//3. launch rmi registry from command line
//  rmiregistry 9999

//4. launch main() method.

//5. connect using JConsole with this remote url 
// service:jmx:rmi:///jndi/rmi://localhost:9999/server

import com.sun.jdmk.comm.HtmlAdaptorServer;

public class JMXAccessUsingRMI {
	private MBeanServer mbs = null;

	public JMXAccessUsingRMI() {
		// Получить экземпляр MBeanServer
		mbs = ManagementFactory.getPlatformMBeanServer();
		HtmlAdaptorServer adapter = new HtmlAdaptorServer();

		// Создаем наш MBean
		Hello helloBean = new Hello();
		ObjectName helloName = null;

		try {
			// И регистрируем его на платформе MBeanServer
			helloName = new ObjectName("SimpleAgent:name=hellothere");
			mbs.registerMBean(helloBean, helloName);

			// Здесь мы создаем RMI connector
			JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/server");
			JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
			cs.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Это вспомогательный метод - он позволяет нашей программе
	// остановиться и ждать
	private static void waitForEnterPressed() {
		try {
			System.out.println("Press  to continue...");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String argv[]) {
		JMXAccessUsingRMI agent = new JMXAccessUsingRMI();
		System.out.println("SimpleAgent is running...");
		JMXAccessUsingRMI.waitForEnterPressed();
	}
}
