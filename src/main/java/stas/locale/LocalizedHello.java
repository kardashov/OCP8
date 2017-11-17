package stas.locale;

import java.util.*;

public class LocalizedHello {
	public static void main(String args[]) {
		Locale currentLocale = Locale.getDefault();
		Locale.setDefault(Locale.ITALY);
		ResourceBundle resBundle = ResourceBundle.getBundle("ResourceBundle",
				Locale.ITALY);
		System.out.printf(resBundle.getString("Greeting"));
	}
}
