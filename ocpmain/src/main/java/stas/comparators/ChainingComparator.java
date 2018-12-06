package stas.comparators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Squirrel {
    private int weight;
    private String species;


    @Override
    public String toString() {
        return species + "=" + weight;
    }

    public Squirrel(String theSpecies, int weight) {
        if (theSpecies == null)
            throw new IllegalArgumentException();
        species = theSpecies;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getSpecies() {
        return species;
    }


}

// Since 1.8
// https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html#comparing-java.util.function.Function-
public class ChainingComparator implements Comparator<Squirrel> {
    public int compare(Squirrel s1, Squirrel s2) {

        Comparator<Squirrel> c = Comparator.comparing(s -> s.getSpecies());
	/*	comparing(...) Accepts a function that extracts a Comparable sort key from a type T, 
		and returns a Comparator<T> that compares by that sort key.*/
        c = c.thenComparingInt(s -> s.getWeight());
							 /*Returns a lexicographic-order comparator with 
							 a function that extracts a int sort key*/
//							 c = c.reversed()
        ;
        return c.compare(s1, s2);
    }

    public static void main(String[] args) {
        List<Squirrel> list = new ArrayList<>();
        list.add(new Squirrel("zebra", 2));
        list.add(new Squirrel("Zebra", 2));
        list.add(new Squirrel("Ze", 2));
        list.add(new Squirrel("Ze", 3));

//		Collections.sort(list);//DOES NOT COMPILE because Squirell is not Comparable<E>
        System.out.println(list);// [zebra=2, Zebra=2, Ze=2, Ze=3]

        Collections.sort(list, new ChainingComparator());
        System.out.println(list); //[Ze=2, Ze=3, Zebra=2, zebra=2]

        Collections.sort(list, new ChainingComparator().reversed());
        System.out.println(list);//[zebra=2, Zebra=2, Ze=3, Ze=2]


    }
}

