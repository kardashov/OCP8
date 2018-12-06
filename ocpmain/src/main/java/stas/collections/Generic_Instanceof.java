package stas.collections;

import java.util.*;

public class Generic_Instanceof {
    public static void main(String[] args) {

        List<Integer> si = new ArrayList<Integer>();
        List<Set<?>> v = new ArrayList<>();


        Set<String> set = new HashSet<>();
        v.add(set);

        System.out.println(v.getClass().getName());

/*		if (v instanceof Collection<String>) { //DOES NOT COMPILE
			
			 * Cannot perform instanceof check against parameterized type
			 * Collection<String>. Use the form Collection<?> instead since
			 * further generic type information will be erased at runtime
			 
			System.out.println("v is Collection");
		}*/

        if (v instanceof Collection<?>) {
            System.out.println("v is Collection");
        }

        if (v instanceof List) {
            System.out.println("v is List");
        }

        v.add(new HashSet<String>());
        ((HashSet<String>) (v.get(0))).add("S1");

        System.out.println(v);
        System.out.println(v.getClass().getName());
        System.out.println(v.get(0).getClass().getName());

        List<? extends Number> intList = Collections.nCopies(5, new Integer(10));

        List<Object> objList = new ArrayList<Object>();

        for (int i = 0; i < 5; i++) {
            objList.add(new Object());
        }

        Collections.copy(objList, intList);

        System.out.println("The objList is: " + objList);

    }
}
