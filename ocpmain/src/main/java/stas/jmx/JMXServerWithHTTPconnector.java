package stas.jmx;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class JMXServerWithHTTPconnector {
    private MBeanServer mbs = null;

    /**
     * http://java-course.ru/articles/jmx/
     * <p>
     * JMX management tools available at URL:
     * http://localhost:8000
     */
    public JMXServerWithHTTPconnector() {
        // �������� ��������� MBeanServer
        mbs = ManagementFactory.getPlatformMBeanServer();
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();

        // ������� ��� MBean
        Hello helloBean = new Hello();
        ObjectName adapterName = null;
        ObjectName helloName = null;

        try {
            // � ������������ ��� �� ��������� MBeanServer
            helloName = new ObjectName("SimpleAgent:name=hellothere");
            mbs.registerMBean(helloBean, helloName);

            // ������ �� ������������ ���������, �������
            // ����� �������� �� HTTP-���������
            adapterName = new ObjectName("SimpleAgent:name=htmladapter,port=8000");
            adapter.setPort(8000);
            mbs.registerMBean(adapter, adapterName);
            adapter.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ��� ��������������� ����� - �� ��������� ����� ���������
    // ������������ � �����
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