package stas.inner.classes;

public class CaseOfThePrivateInterface {
	private interface Secret {
		public void shh();//all methods in an interface are public still applies
	}

	class DontTell implements Secret {
		public void shh() {	}  //A class that implements
								//the interface must define that method as public .
	}
}

