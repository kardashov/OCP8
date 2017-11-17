package stas.exceptions;
public class _BasicExceptions2 {
	public static void main(String[] args) {
		System.out.println(exceptions());
	}

	public Error returnError() {
		return new Error();   //LEGAL STATEMENT
	}
/*	Any Java type, including Exception, can
	be declared as the return type. However, this will simply return the object rather than
	throw an exception. */
	
	
	public static String exceptions() {
		String result = "";
		String v = null;
		try {
			try {
				result += "before";
				v.length();
				result += "after";
			} catch (NullPointerException e) {
				result += "catch";
				throw new RuntimeException();
			} finally {
				result += "finally";  // this executes ALWAYS!!!
				throw new Exception();
			}
		} catch (Exception e) {
			result += "done";
		}
		return result;
	}
}
