package stas.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

public class Constructors {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {


        Class<String> stringclass = String.class;
        // get all visible constructors
        Constructor<?>[] constructors = stringclass.getConstructors();

        //all constructors
        Constructor<?>[] declaredConstructors = stringclass.getDeclaredConstructors();

        for (Constructor<?> constructor : constructors) {
            int numberParams = constructor.getParameterCount();
            System.out.println("constructor " + constructor.getName());
            System.out.println("number of arguments " + numberParams);
            // public, private, etc.
            int modifiersConstructor = constructor.getModifiers();
            System.out.println("modifiers " + modifiersConstructor);
            // array of parameters, more info in the methods section
            Parameter[] parameters = constructor.getParameters();
            // annotations array, more info in the annotations section
            Annotation[] annotations = constructor.getAnnotations();

        }
        // can be used to create new instances (no params in this case)
//		String danibuizaString = (String)constructor.newInstance( );

    }

}
