package stas.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StaticReflection {

    static class StaticExample {
        int counter;
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {

        // 1 access static class
        System.out.println("directly " + StaticExample.class.getName());

        // 2 using for name directly throws an exception
        Class<?> forname = Class.forName("com.danibuiza.javacodegeeks.reflection.StaticReflection.StaticExample");

        // 3 using $ would work but is not that nice
        forname = Class.forName("com.danibuiza.javacodegeeks.reflection.StaticReflection$StaticExample");

        // 4 another way iterating through all classes declared inside this
        // class
        Class<?>[] classes = StaticReflection.class.getDeclaredClasses();
        for (Class<?> class1 : classes) {
            System.out.println("iterating through declared classes " + class1.getName());
        }

        // access static methods in the same way as instance ones
        Method mathMethod = Math.class.getDeclaredMethod("round", double.class);

        // methods: object instance passed can be null since method is static
        Object result = mathMethod.invoke(null, new Double(12.4));
        // static field access, instance can be null
        Field counterField = StaticExample.class.getDeclaredField("counter");
        System.out.println(counterField.get(null));
    }

}
