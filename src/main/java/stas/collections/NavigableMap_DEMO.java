package stas.collections;

import java.util.LinkedList;
import java.util.NavigableMap;
import java.util.TreeMap;

public class NavigableMap_DEMO {
	public static void main(String[] args) {
		NavigableMap<Integer, String> examScores = new TreeMap<Integer, String>();

		examScores.put(90, "Sophia");
		examScores.put(20, "Isabella");
		examScores.put(10, "Emma");
		examScores.put(50, "Olivea");

		System.out.println("The data in the map is: " + examScores);
		System.out.println("The data descending order is: " + examScores.descendingKeySet());
		System.out.println("Details of those who passed the exam: " + examScores.tailMap(40));
		System.out.println("The lowest mark is: " + examScores.firstEntry());
		
		
		examScores.tailMap(40).clear(); //{10=Emma, 20=Isabella}  Olivia and Sophia removed
		System.out.println(examScores);
		
		
		examScores.descendingKeySet().clear(); //cleans all map
		System.out.println(examScores);   // {} 
		
	}
}
