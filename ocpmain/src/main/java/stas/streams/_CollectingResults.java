package stas.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _CollectingResults {
    public static void main(String[] args) {

        System.out.println("================= Collectors.joining() =====================");
        Stream<String> ohMy = Stream.of("lions", "tigers", "bears");

        String result = ohMy.collect(Collectors.joining(", "));
        System.out.println(result); // lions, tigers, bears
/*		We pass the predefined joining() collector to the collect() method. 
		All elements of the stream are then merged into a String with the specified 
		delimiter between each element.*/
        ohMy = Stream.of("lions", "tigers", "bears");
        result = ohMy.collect(Collectors.joining());
        System.out.println(result);// lionstigersbears     -- no delimeters by default

        ohMy = Stream.of("lions", "tigers", "bears");
        result = ohMy.collect(Collectors.joining("-", "[", "]"));
        System.out.println(result);//[lions-tigers-bears]    -- using delimeter, prefix [ and suffix ]

        System.out.println("================= Collectors.averagingInt() ================");
        ohMy = Stream.of("lions", "tigers", "bears");
        Double result1 = ohMy.collect(Collectors.averagingInt(String::length));
        System.out.println(result1); // 5.333333333333333
		/*We pass a collector to collect() and it performs the average
		for us. This time, we needed to pass a function to tell the collector what to average. We
		used a method reference, which returns an int upon execution. With primitive streams,
		the result of an average was always a double, regardless of what type is being averaged.*/


        System.out.println("================= Collectors.toCollection() ================");
        ohMy = Stream.of("lions", "tigers", "bears");
        TreeSet<String> result2 = ohMy.filter(s -> s.startsWith("t"))
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(result); // [tigers]

        System.out.println("================= Collecting into Maps =====================");
        ohMy = Stream.of("lions", "tigers", "bears");
        Map<String, Integer> map = ohMy.collect(Collectors.toMap(s -> s, String::length));
        System.out.println(map); // {lions=5, bears=5, tigers=6}
		/*When creating a map, you need to specify two functions. The first function tells the
		collector how to create the key. The second function tells the collector how to create the value.*/

        ohMy = Stream.of("lions", "tigers", "bears2", "tigers", "tigers");
        //using toMap() version with merge function
        Map<String, Integer> map1 = ohMy
                .collect(Collectors.toMap(s -> s, String::length, (a, b) -> b + a, TreeMap::new));
        System.out.println(map1);//{bears2=6, lions=5, tigers=18} --> tigers = 6 + 6 + 6


//		Now we want to do the reverse and map the length of the animal name to the name	itself. 
        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, String> map2;
//		map2 = ohMy.collect(Collectors.toMap(String::length, k -> k)); // THROWS EXCEPTION
//		Exception in thread "main" java.lang.IllegalStateException: Duplicate key lions
//		at java.util.stream.Collectors.lambda$throwingMerger$114


        ohMy = Stream.of("lions", "tigers", "bears", "hobbit");
        map2 = ohMy.collect(Collectors.toMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2));
        System.out.println(map2); // {5=lions,bears, 6=tigers,hobbit}
        System.out.println(map2.getClass()); // class. java.util.HashMap
		
/*		It so happens that the Map returned is a HashMap . This behavior is not guaranteed.
		Suppose that we want to mandate that the code return a TreeMap instead. No problem. We
		would just add a constructor reference as a parameter:*/
        ohMy = Stream.of("lions", "tigers", "bears");
        map2 = ohMy.collect(Collectors.toMap(String::length, //key function
                k -> k,        //value generator
                (s1, s2) -> s1 + "," + s2, //merge function
                TreeMap::new));  //destination map fabrica
        System.out.println(map2); // // {5=lions,bears, 6=tigers}
        System.out.println(map2.getClass()); // class. java.util.TreeMap


        System.out.println("======== Collecting Using Grouping, Partitioning, and Mapping =====");
        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, List<String>> map5 =
                ohMy.collect(Collectors.
                        groupingBy(String::length)); //GROUPING  FUNCTION
        System.out.println(map5); // {5=[lions, bears], 6=[tigers]}
        System.out.println(map5.getClass().getName());//java.util.HashMap
		
/*The groupingBy() collector tells collect() that it should group all of the elements of
the stream into lists, organizing them by the function provided. This makes the keys in the
map the function value and the values the function results.

Suppose that we don�t want a List as the value in the map and prefer a Set instead. No
problem. There�s another method signature that lets us pass a downstream collector. This is
a second collector that does something special with the values:*/
        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, Set<String>> map6 = ohMy.collect(
                Collectors.groupingBy(String::length, Collectors.toSet()));
        System.out.println(map6); // {5=[lions, bears], 6=[tigers]}
        System.out.println(map6.getClass().getName());//java.util.HashMap
        System.out.println(map6.get(6).getClass().getName());//java.util.HashSet

//We can even change the type of Map returned through yet another parameter:
        ohMy = Stream.of("lions", "tigers", "bears");
        TreeMap<Integer, Set<String>> map7 = ohMy
                .collect(Collectors.groupingBy(
                        String::length,        //grouping function (keys of resulting Map)
                        TreeMap::new,    //factory function (gives target Map object)
                        Collectors.toSet())); //downstream Collector to collect values for each Key
        System.out.println(map7); // {5=[lions, bears], 6=[tigers]}
		
/*This is very flexible. What if we want to change the type of Map returned but leave the
type of values alone as a List? */

        ohMy = Stream.of("lions", "tigers", "bears");
        TreeMap<Integer, List<String>> map8 =
                ohMy.collect(Collectors.groupingBy(
                        String::length, //grouping function
                        TreeMap::new,   //factory for
                        Collectors.toList()));
        System.out.println(map8); //{5=[lions, bears], 6=[tigers]}

        System.out.println("================ PARTITIONING ===========================");
/*Partitioning is a special case of grouping. With partitioning, there are only two possible
groups�true and false. Partitioning is like splitting a list into two parts.*/
        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Boolean, List<String>> map9 = ohMy.collect(
                Collectors.partitioningBy(s -> s.length() <= 5));
        System.out.println(map9); // {false=[tigers], true=[lions, bears]}


//  NOW with 7 chars per word
        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Boolean, List<String>> map10 = ohMy.collect(
                Collectors.partitioningBy(s -> s.length() <= 7));
        System.out.println(map10); // {false=[], true=[lions, tigers, bears]}

//As with groupingBy(), we	can change the type of List to something else:		
        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Boolean, Set<String>> map12 = ohMy.collect(
                Collectors.partitioningBy(s -> s.length() <= 7,
                        Collectors.toSet()));
        System.out.println(map12);// {false=[], true=[lions, tigers, bears]}

// using counting collector		
        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, Long> map13 = ohMy.collect(Collectors.groupingBy(
                String::length, Collectors.counting()));
        System.out.println(map13); // {5=2, 6=1}

        System.out.println("============= Collectors.mapping() collector ==================");
/*		mapping(Function f, Collector dc) : Collector
	Adds another level of collectors. Adapts a Collector accepting elements 
	of type U to one accepting elements of type T by applying a mapping function 
	to each input element before accumulation*/
        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, Optional<Character>> map15 = ohMy.collect(
                Collectors.groupingBy(String::length,
                        Collectors.mapping(s -> s.charAt(0),
                                Collectors.minBy(Comparator.naturalOrder()))));
        System.out.println(map15); // {5=Optional[b], 6=Optional[t]}


        String sentence1 = "Carpe diem. Seize the day, boys. Make your lives extraordinary.";
        String sentence2 = "Frankly, my dear, I don't give a damn!";
        String sentence3 = "Do I look like I give a damn?";
        List<String> sentences = Arrays.asList(sentence1, sentence2, sentence3);

        Stream<String> strm = sentences.stream().flatMap(str -> Stream.of(str.split("[ ,.!?\r\n]")))
                .filter(s -> s.length() > 0).distinct();
        strm.forEach(System.out::println);


    }
}
