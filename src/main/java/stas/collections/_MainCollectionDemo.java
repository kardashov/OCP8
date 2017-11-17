package stas.collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class _MainCollectionDemo {

	// also see stas.arrays.ArrayManipulation.java
	public static void main(String[] args) {

		System.out.println("=================Using List interface===================================");
		List<String> list = new ArrayList<>();
		list.add("SD"); // [SD]
		list.add(0, "NY"); // [NY,SD]
		list.set(1, "FL"); // [NY,FL]
		list.remove("NY"); // [FL]
		list.remove(0); // []

		list.add("OH");// [OH]
		list.add("CO");// [OH,CO]
		list.add("NJ");// [OH,CO,NJ]
		System.out.println(list);

		String state = list.get(0);// OH
		int index = list.indexOf("NJ");// 2
		
		System.out.println("=============== Updating Sublist view =====================");
		List<String> vowels = new ArrayList<String>();
		vowels.add("a");
		vowels.add("e");
		vowels.add("i");
		vowels.add("o");
		vowels.add("u");
		Function<List<String>, List<String>> f = cc -> cc.subList(2, 4);
		List<String> view = f.apply(vowels);//get a view backed by the original list 
		view.add("x");//modify the view 
		vowels.forEach(System.out::print); //updates visible in original list
		
		System.out.println("===============Using Set interface  with HashSet=============");
		// Set preserves only unique elements and discards all duplicates
		Set<Integer> set = new HashSet<>();
		boolean b1 = set.add(66); // true
		boolean b2 = set.add(10); // true
		boolean b3 = set.add(66); // false
		boolean b4 = set.add(8); // true
		for (Integer integer : set)
			System.out.print(integer + ","); // 66,8,10,

		System.out.println("\n===============Using Set interface  with TreeSet=============");
		Set<Integer> set1 = new TreeSet<>();
		boolean b01 = set1.add(66); // true
		boolean b02 = set1.add(10); // true
		boolean b03 = set1.add(66); // false
		boolean b04 = set1.add(8); // true
		for (Integer integer : set1)
			System.out.print(integer + ","); // 8,10,66, - Elements sorted in
												// their natural sorted order!!!

		System.out.println("\n Using NavigableSet");
		NavigableSet<Integer> set3 = new TreeSet<>();
		for (int i = 1; i <= 20; i++)
			set3.add(i); // set contains 20 integers
		System.out.println(set3.lower(10)); // 9
		System.out.println(set3.floor(10)); // 10
		System.out.println(set3.ceiling(20)); // 20
		System.out.println(set3.higher(20)); // null

		System.out.println("\n Using the Queue Interface");
		Queue<Integer> queue = new ArrayDeque<>();
		System.out.println(queue.offer(10)); // true
		System.out.println(queue.offer(4)); // true
		System.out.println(queue.peek()); // 10
		System.out.println(queue.poll()); // 10
		System.out.println(queue.poll()); // 4
		System.out.println(queue.peek()); // null
		System.out.println(queue.poll()); // null
		// System.out.println(queue.element()); //throws Exception

		System.out.println("\n Using the ArrayDeque Interface as Stack");
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		stack.push(10);
		stack.push(4);
		System.out.println(stack.peek()); // 4
		System.out.println(stack.poll()); // 4
		System.out.println(stack.poll()); // 10
		System.out.println(stack.peek()); // null
		System.out.println(stack.poll()); // null
		// stack.pop(); //throws NoSuchElementException

		System.out.println("\n Using the Map Interface and HashMap");
		Map<String, String> map = new HashMap<>();
		map.put("koala", "bamboo");
		map.put("lion", "meat");
		map.put("giraffe", "leaf");
		String food = map.get("koala"); // bamboo
		for (String key : map.keySet())
			System.out.print(key + ","); // koala,giraffe,lion,

		System.out.println("\n Using the Map Interface and TreeMap");
		Map<String, String> map1 = new TreeMap<>();
		map1.put("koala", "bamboo");
		map1.put("lion", "meat");
		map1.put("giraffe", "leaf");
		map1.put("rediska", null);
		food = map1.get("koala"); // bamboo
		for (String key : map1.keySet())
			System.out.print(key + ","); // giraffe,koala,lion, --SORTED

		// System.out.println(map1.contains("lion")); //method contains(Object)
		// not defined in Map
		System.out.println(map1.containsKey("lion")); // true
		System.out.println(map1.containsValue("lion"));// fal
		System.out.println(map1.size());

		map1.merge("lion", "Candy", (a, b) -> {
			System.out.println(a + "-->" + b);
			return "salo";});// "lion=meat" --> lion=salo
		
		map1.merge("lion", "Candy", String::concat);// "lion=salo" --> lion=saloCandy

		map1.merge("rediska", "Candy", (a, b) -> "salo");// rediska=null --> rediska=Candy

		System.out.println(map1);

	}
}
