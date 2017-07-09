package stas.inner.classes;

public class Outer2 {
	private int length = 5;

	public void calculate() { //method begin
	/*final*/int width = 20;//this field is "effectively final" even without final keyword
				//width = 5; //if this line added, than "width" variable cannot be used
									// inside inner class. See "Effectively final" concept.
		
		//========================Inner class definition start==============
			class Inner {
				int i;//can declare non-static fields
			//static int i; // ERROR The field cannot be declared static in a non-static inner type
			public void multiply() {
				System.out.println(length * width);
				System.out.println(this.i);
			}
		}
		//========================Inner class definition end================
		Inner inner = new Inner();
		inner.multiply();
	}//Inner class’s scope ends here   //method end

	public static void main(String[] args) {
		Outer2 outer = new Outer2();
		
		outer.calculate();
	}
}


