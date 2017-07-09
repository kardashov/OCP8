package stas.locale;

import java.util.*;
import stas.locale.*;
public class LocalizedHello2 {
	public static void printMovieDetails(ResourceBundle resBundle) {
		String movieName = resBundle.getString("MovieName");
		Long revenue = (Long) (resBundle.getObject("GrossRevenue"));
		Integer year = (Integer) resBundle.getObject("Year");

		System.out.println("Movie " + movieName + "(" + year + ")"
				+ " grossed " + revenue);
	}

	public static void main(String args[]) {
		// print the largest box-office hit movie for default (US) locale
		Locale locale = Locale.getDefault();
		printMovieDetails(ResourceBundle.getBundle("stas.locale.ResBundle", locale));

		// print the largest box-office hit movie for Italian locale
		locale = new Locale("it", "IT", "");
		printMovieDetails(ResourceBundle.getBundle("stas.locale.ResBundle", locale));
	}
}
//Step 1: The search starts by looking for an exact match for the resource bundle with the full name.
//Step 2: The last component (the part separated by _) is dropped and the search is repeated with the resulting shorter name. 
//		  This process is repeated till the last locale modifier is left.
//Step 3: The search is restarted using the full name of the bundle for the default locale.
//Step 4: Search for the resource bundle with just the name of the bundle.
//Step 5: The search fails, throwing a MissingBundleException.
