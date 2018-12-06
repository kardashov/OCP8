package stas.reflection;

import java.lang.reflect.Proxy;

public class MyDynamicProxyTest {

    public static void main(String[] args) {
        // http://tutorials.jenkov.com/java-reflection/dynamic-proxies.html

//		http://docs.oracle.com/javase/8/docs/technotes/guides/reflection/proxy.html
        MyDynamicProxyInterface jj = (MyDynamicProxyInterface) Proxy.newProxyInstance(MyDynamicProxyInterface.class.getClassLoader(),
                new Class[]{MyDynamicProxyInterface.class}, new MyDynamicProxyInvokationHandlerImpl());

        MyDynamicProxyInterface gg = (MyDynamicProxyInterface) Proxy.newProxyInstance(MyDynamicProxyInterface.class.getClassLoader(),
                new Class[]{MyDynamicProxyInterface.class}, new MyDynamicProxyInvokationHandlerImpl());


        System.out.println(jj.toString());
        jj.ProxyInterfaceMethod();

        System.out.println(gg.toString());
        gg.ProxyInterfaceMethod();
    }

}
