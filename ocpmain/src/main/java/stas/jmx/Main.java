package stas.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class Main {
    // https://docs.oracle.com/javase/tutorial/jmx/mbeans/standard.html
// http://java-course.ru/articles/jmx/
// http://static.codingthearchitecture.com/presentations/qcon2007-what-can-jmx-do-for-you.pdf
    public static void main(String[] args) throws Exception {

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName name = new ObjectName("stas.jmx:type=Hello");
        Hello mbean = new Hello();
        mbs.registerMBean(mbean, name);

        System.out.println("Waiting forever...");
        Thread.sleep(Long.MAX_VALUE);
    }
}