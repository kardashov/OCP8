package stas.comparators;

import java.util.Set;
import java.util.TreeSet;

public class UseTreeSet {
	static class Rabbit {
		int id;
	}

	public static void main(String[] args) {
		Set<Duck> ducks = new TreeSet<>();
		ducks.add(new Duck("Puddles", 223));
		
		Set<Rabbit> rabbit = new TreeSet<>();
		rabbit.add(new Rabbit()); // throws an exception
//		Exception in thread "main" java.lang.ClassCastException: comparing.Rabbit cannot
//		be cast to java.lang.Comparable
	}
}


