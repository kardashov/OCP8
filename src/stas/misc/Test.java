package stas.misc;

public class Test {

	public static void main(String[] args) {

		String n = "sssssssss";
		String f = n;

		System.out.println(" n = " + n.hashCode());
		System.out.println(" f = " + f.hashCode());

		if (n == f) {
			System.out.println("equals");

		}
	}
	public String toString() {
        return "x ";
}
	
	
}
