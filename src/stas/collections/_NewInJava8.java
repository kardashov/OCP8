package stas.collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import stas.comparators.Duck;

public class _NewInJava8 {
	static final Comparator<Duck> byWeightStatic = (d1, d2) -> DuckHelper.compareByWeight(d1, d2);
	
	public static void main(String[] args) {
		/*		four formats for method references:
		 Static methods
		 Instance methods on a particular instance
		 Instance methods on an instance to be determined at runtime
		 Constructors*/
		Comparator<Duck> byWeight = (d1, d2) -> DuckHelper.compareByWeight(d1, d2);
		Comparator<Duck> byWeight2 = DuckHelper::compareByWeight;
		Comparator<Duck> byWeight3 = byWeightStatic;
		
//		We call a method with one parameter, and Java knows that it should create a
//		lambda with one parameter and pass it to the method
		Consumer<List<Integer>> methodRef1 = Collections::sort;
		Consumer<List<Integer>> lambda1 = l -> Collections.sort(l);
		
//		calling an instance method on a specific instance
		String str = "abc";
		Predicate<String> methodRef2 = str::startsWith;
		Predicate<String> lambda2 = s -> str.startsWith(s);
		
/*		we call an instance method without knowing the instance in advance:
		Java knows that isEmpty is an instance method that does not
		take any parameters. Java uses the parameter supplied at runtime as the instance on which
		the method is called*/
		
//		Predicate<String> methodRef3 = String::concat;//DOES NOT COMPILE
		Predicate<String> methodRef3 = String::isEmpty;
		Predicate<String> lambda3 = s -> s.isEmpty();

		BiPredicate<String, String> c3 = String::startsWith;
		System.out.println(c3.test("Hello", "H"));//true
		
		
//		A constructor reference is a special type of method reference that uses new instead of a
//		method, and it creates a new object:
		Supplier<ArrayList> methodRef4 = ArrayList::new;
		Supplier<ArrayList> lambda4 = () -> new ArrayList();
		
		
//		boolean removeIf(Predicate<? super E> filter)
		List<String> list = new ArrayList<>();
		list.add("Magician");
		list.add("Assistant");
		System.out.println(list); // [Magician, Assistant]
		list.removeIf(s -> s.startsWith("A"));
//		you can’t replace lambda with a method reference? 
		
		System.out.println(list); // [Magician]
		
		System.out.println("==============Updating All Elements with replaceAll==============");
//		void replaceAll(UnaryOperator<E> o)
		List<Integer> list2 = Arrays.asList(1, 2, 3);
		list2.replaceAll(x -> x*2);
		System.out.println(list2); // [2, 4, 6]
		
		System.out.println("===================Looping through a Collection====================");
		List<String> cats = Arrays.asList("Annie", "Ripley");
		
		cats.forEach(c -> System.out.println(c));
		cats.forEach(System.out::println);
		
		
		System.out.println("======================Using Map methods===========================");
		Map<String, String> favorites = new HashMap<>();
		favorites.put("Jenny", "Bus Tour");
//		replace the existing value unconditionally
		favorites.put("Jenny", "Tram");
		System.out.println(favorites); // {Jenny=Tram}
		
		System.out.println("=================Using Map.putIfAbsent() method=================");
		favorites = new HashMap<>();
		favorites.put("Jenny", "Bus Tour");
		favorites.put("Tom", null);
		favorites.putIfAbsent("Jenny", "Tram");
		favorites.putIfAbsent("Sam", "Tram");
		favorites.putIfAbsent("Tom", "Tram");
		System.out.println(favorites); // {Tom=Tram, Jenny=Bus Tour, Sam=Tram}
		
		System.out.println("=================Using Map.merge() method=================");
		
		BiFunction<String, String, String> mapper = (v1, v2)-> v1.length() > v2.length() ? v1: v2;
		
		
		favorites = new HashMap<>();
		favorites.put("Jenny", "Bus Tour");
		favorites.put("Tom", "Tram");
		String jenny = favorites.merge("Jenny", "Skyride", mapper);
		String tom = favorites.merge("Tom", "Skyride", mapper);	
		System.out.println(favorites); // {Tom=Skyride, Jenny=Bus Tour}
		System.out.println(jenny); // Bus Tour
		System.out.println(tom); // Skyride
		
		System.out.println("=================Map.merge() and NULL values=================");
		favorites = new HashMap<>();
		favorites.put("Sam", null);
		favorites.merge("Tom", "Skyride", mapper);
		favorites.merge("Sam", "Skyride", mapper);
		System.out.println(favorites); // {Tom=Skyride, Sam=Skyride}
		
		System.out.println("=================Map.merge() if Mapper returns null============");
//		when the mapping function is called and returns null, the KEY IS REMOVED from the map
		mapper = (v1, v2) -> null;
		favorites = new HashMap<>();
		favorites.put("Jenny", "Bus Tour");
		favorites.put("Tom", "Bus Tour");
		favorites.merge("Jenny", "Skyride", mapper);
		favorites.merge("Sam", "Skyride", mapper);
		System.out.println(favorites); // {Tom=Bus Tour, Sam=Skyride}
		
		System.out.println("=================Map.forEach() takes BiConsumer===============");
		favorites.forEach((a,b) -> System.out.println( a + " " + b));
		
		System.out.println("=================Map.computeIfPresent() method===============");
/*		If the value for the specified key is present and non-null, 
		attempts to compute a new mapping given the key and its current mapped value.

		If the function returns null, the mapping is removed. If the function itself 
		throws an (unchecked) exception, the exception is rethrown, and the current mapping is left unchanged.*/
		
		Map<String, Integer> counts = new HashMap<>();
		counts.put("Jenny", 1);
		
		BiFunction<String, Integer, Integer> mapper1 = (k, v) -> v + 1;
		
		Integer jenny1 = counts.computeIfPresent("Jenny", mapper1);
		Integer sam = counts.computeIfPresent("Sam", mapper1);
		
		System.out.println(counts); // {Jenny=2}
		System.out.println(jenny1); // 2
		System.out.println(sam); // null
		
		System.out.println("=================Map.computeIfAbsent() method===============");
//		For computeIfAbsent(), the functional interface runs only when the key isn’t present or is null :
//		Since there is no value already in the map, a Function is used instead of a
//		BiFunction . Only the key is passed as input:
		
/*		If the specified key is not already associated with a value (or is mapped to null),
		attempts to compute its value using the given mapping function and enters it into this map unless null.

		If the function returns null no mapping is recorded. If the function itself 
		throws an (unchecked) exception, the exception is rethrown, and no mapping is recorded. */
		Function<String, Integer> mapper3 = (k) -> 1;
		counts = new HashMap<>();
		counts.put("Jenny", 15);
		counts.put("Tom", null);
		

		
		Integer jenny3 = counts.computeIfAbsent("Jenny", mapper3); // 15
		Integer sam3 = counts.computeIfAbsent("Sam", mapper3); // 1        //key is added by computeIfAbsent
		Integer tom3 = counts.computeIfAbsent("Tom", mapper3); // 1
		System.out.println(counts); // {Tom=1, Jenny=15, Sam=1}
		
		System.out.println("=================Map.computeIfAbsent and computeIfPresent and NULL===============");
/*		If the mapping function is called and returns null , the key is removed from the map for
		computeIfPresent() . For computeIfAbsent() , the key is never added to the map in the first place*/
		counts = new HashMap<>();
		counts.put("Jenny", 1);
		
		
		counts.computeIfPresent("Jenny", (k, v) -> null);   // Jenny is removed
		counts.computeIfAbsent("Sam", k -> null);			//Sam is not added
		System.out.println(counts); // {}
		counts.put("Sam", null);
		counts.computeIfAbsent("Sam", k -> null);
		System.out.println(counts); //   {Sam=null}   // Sam also NOT REMOVED
//		After running this code, the map is empty. The call to computeIfPresent() removes the
//		key from the map. The call to computeIfAbsent() doesn’t add a key.
		
		if (counts.containsKey("Sam") && counts.get("Sam") == null) {
			System.out.println("Hello!");
		}
		counts.replace("Sam", null, 2);
		System.out.println(counts);
		
		
	}
}

//===================================================================================
class DuckHelper {
	public static int compareByWeight(Duck d1, Duck d2) {
		return d1.getWeight() - d2.getWeight();
	}

	public static int compareByName(Duck d1, Duck d2) {
		return d1.getName().compareTo(d2.getName());
	}
}