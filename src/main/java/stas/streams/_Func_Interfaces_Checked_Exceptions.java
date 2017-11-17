package stas.streams;

import java.io.*;
import java.util.*;
import java.util.function.Supplier;

public class _Func_Interfaces_Checked_Exceptions {

	public static void main(String[] args) throws IOException {

		ExceptionCaseStudy.create().stream().count(); // compiles fine

		// Supplier<List<String>> s = ExceptionCaseStudy::create; // DOES NOT COMPILE

		/* The actual compiler error is <code> unhandled exception type * IOException.
		 * The problem is that the lambda to which
		 * this method reference expands does declare an exception. The Supplier
		 * interface does not allow checked exceptions. There are two approaches
		 * to get around this problem. One is to catch the exception and turn it
		 * into an unchecked exception:
		 */
		Supplier<List<String>> s1 = () -> {
			try {
				return ExceptionCaseStudy.create();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		};
		
		Supplier<List<String>> s2 = ExceptionCaseStudy::createSafe; 
//		This works, createSafe doesn't throw checked exceptions
	}

}

class ExceptionCaseStudy {
	static List<String> create() throws IOException {
		throw new IOException();
	}

	static List<String> createSafe() {
		try {
			return ExceptionCaseStudy.create();
		} catch (IOException e) {     //translate checked exception to unchecked exception
			throw new RuntimeException(e);
		}
	}
}