package stas.exceptions;

public class PrintingExceptions {
	public static void main(String[] args) {
		try {
			hop();
		} catch (Exception e) {
			System.out.println(e); // Prints the exception type and message
			System.out.println(e.getMessage()); //prints only message
			e.printStackTrace();//prints full stack
		}
	}

	private static void hop() {
		throw new RuntimeException("cannot hop");
	}
}
