package stas.comparators;

import java.util.Arrays;
import java.util.Collections;

public class NullComparatorInSort {
    public static void main(String[] args) {
        Object[] sa = {100, 100.0, "100"};
        Collections.sort(Arrays.asList(sa), null);//if Comparator is null
        // than natural ordering of elements will be used


        System.out.println(sa[0] + " " + sa[1] + " " + sa[2]);
    }
}


/*Exception in thread "main" java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.Double
at java.lang.Double.compareTo(Double.java:49)
at java.util.ComparableTimSort.countRunAndMakeAscending(ComparableTimSort.java:320)
at java.util.ComparableTimSort.sort(ComparableTimSort.java:188)
at java.util.Arrays.sort(Arrays.java:1246)
at java.util.Arrays.sort(Arrays.java:1433)
at java.util.Arrays$ArrayList.sort(Arrays.java:3895)
at java.util.Collections.sort(Collections.java:175)
at stas.comparators.NullComparatorInSort.main(NullComparatorInSort.java:9)
*/