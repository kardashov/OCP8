package stas.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IteratorTest {

	public static void main(String[] args) {
		// create a list, use is ArrayList as concrete type
		// ArrayList<> infers the String type from the left side

		List<Integer> var = new ArrayList<>();

		// add a few Strings to it
		for (int i = 0; i < 500; i++) {
			// var.add((int) (Math.random() * 1000));
			var.add(i);

		}

		Collections.rotate(var, 20);

		Iterator<Integer> iter = var.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
			iter.remove();
		}

		System.out.println("Size = " + var.size());
		// Loop over it and print the result to the console
		for (int s : var) {
			System.out.println(s);
		}
	}
}
