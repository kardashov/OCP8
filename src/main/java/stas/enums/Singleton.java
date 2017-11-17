package stas.enums;

public enum Singleton {
	instance;

	public static Singleton getInstance() {
		return instance;
	}
	private String a[] = new String[20];
}
