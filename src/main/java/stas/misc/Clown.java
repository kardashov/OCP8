package stas.misc;

class Clown {
	static int hopAround() {
		int i = 0;
		while (true) {
			try {
				try {
					i = 1;
				} finally { // The first finally clause
					i = 2;
				}
				i = 3;
				// This return never completes, because of
				// the continue in the second finally clause
				return i;
			} finally { // The second finally clause
				if (i == 3) {
					// This continue overrides the return statement
//					continue;
			
					
				}
			}
		}
	}
	public static void main(String[] args) {
		System.out.println(hopAround());
	}
}