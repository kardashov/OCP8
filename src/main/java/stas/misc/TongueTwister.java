package stas.misc;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TongueTwister {

	public TongueTwister() {
	}

	public static void main(String[] args) {
		String str = "I feel, a feel, a funny feel, a funny feel I feel, "+
				"if you feel the feel  I feel, I feel the feel you feel";
		Set<String> words  = new TreeSet<>();
		
		for (String string : str.split("\\W+")) {
			words.add(string);
			
		}
		
		
		System.out.println(str);
		System.out.println(words);
		
	}

}
