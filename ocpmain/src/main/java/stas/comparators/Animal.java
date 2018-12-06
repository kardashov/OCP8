package stas.comparators;

/*		The number zero is returned when the current object is equal to the argument to com-
pareTo() .
		A number less than zero is returned when the current object is smaller than the argu-
ment to compareTo() .
		A number greater than zero is returned when the current object is larger than the argu-
ment to compareTo().*/
public class Animal implements Comparable<Animal> {
    private int id;

    public int compareTo(Animal a) {
        return id - a.id;
    }

    public static void main(String[] args) {
        Animal a1 = new Animal();
        Animal a2 = new Animal();
        a1.id = 5;
        a2.id = 7;
        System.out.println(a1.compareTo(a2)); // -2
        System.out.println(a1.compareTo(a1)); // 0
        System.out.println(a2.compareTo(a1)); // 2

    }
}

/*
 * When dealing with legacy code, the compareTo() method requires a cast since
 * it is passed an Object :
 */
class LegacyDuck implements Comparable {  //used by Collections.sort and Arrays.sort)
    private String name;

    public int compareTo(Object obj) {
        LegacyDuck d = (LegacyDuck) obj;
        // cast because no generics
        return name.compareTo(d.name);
    }
}
//Difference 			Comparable 			Comparator
//Package name			 java.lang 			java.util
//Interface must be
//implemented by 
//class comparing? 		Yes 				No
//Method name in 
//interface 			compareTo 			compare
//Number of parameters 		1 					2
//Common to declare 
//using a lambda 			No 				   Yes

















