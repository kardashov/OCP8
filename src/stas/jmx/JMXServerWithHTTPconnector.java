package stas.jmx;

import javax.management.*;
import java.lang.management.*;
import com.sun.jdmk.comm.HtmlAdaptorServer;

public class JMXServerWithHTTPconnector {
	private MBeanServer mbs = null;

	/**
	 * http://java-course.ru/articles/jmx/
	 * 
	 * JMX management tools available at URL:
	 * http://localhost:8000
	 */
	public JMXServerWithHTTPconnector() {
		// Получить экземпляр MBeanServer
		mbs = ManagementFactory.getPlatformMBeanServer();
		HtmlAdaptorServer adapter = new HtmlAdaptorServer();

		// Создаем наш MBean
		Hello helloBean = new Hello();
		ObjectName adapterName = null;
		ObjectName helloName = null;

		try {
			// И регистрируем его на платформе MBeanServer
			helloName = new ObjectName("SimpleAgent:name=hellothere");
			mbs.registerMBean(helloBean, helloName);

			// Теперь мы регистрируем коннектор, который
			// будет доступен по HTTP-протоколу
			adapterName = new ObjectName("SimpleAgent:name=htmladapter,port=8000");
			adapter.setPort(8000);
			mbs.registerMBean(adapter, adapterName);
			adapter.start();

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
		JMXServerWithHTTPconnector agent = new JMXServerWithHTTPconnector();
		System.out.println("SimpleAgent is running...");
		JMXServerWithHTTPconnector.waitForEnterPressed();
	}
}