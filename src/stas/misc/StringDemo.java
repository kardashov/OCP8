package stas.misc;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
//import java.security.Policy;
//java.security.Permission
//java.security.CodeSource
//
//java.io.FilePermission
public  class StringDemo {
  
 
public int somethsing()
	{return 5;}

public static void main (String[] args) {
	System.out.println();
	byte i = 20;
	double f = 20.2;
	boolean m = true;
	
//	Boolean g = false;
//	
//	
//	
//	if (Integer.class.isInstance(i)) {
//		System.out.println("is boolean");
//	}
	StringDemo.class.getName();
	
	String g = "s     ";
	String z = "s   ";
	System.out.print(z.compareTo(g));

//	i = (int) (i + f);
//	System.out.println(((Object)i).getClass().getName());
//	System.out.println(((Object)f).getClass().getName());
//	
	
	//	List<String> list = new ArrayList<>();
//	list.add("vogella.com");
//	list.add("google.com");
//	list.add("heise.de");
//	list.forEach(System.out::println); 
//	
//	String m = "fff";
//	String n = new String("fff");
//	n = n.intern();
//	
//	if (m==n) {
//		System.out.print("Equal");
//				
//	} else {
//		System.out.print("Not equal");
//	}
	
};


//    public static void main(String[] args) {
//        String palindrome = "Dot saw I was Tod";
//        int len = palindrome.length();
//        char[] tempCharArray = new char[len];
//        char[] charArray = new char[len];
//        
//        // put original string in an 
//        // array of chars 
//        for (int i = 0; i < len; i++) {
//            tempCharArray[i] = 
//                palindrome.charAt(i);
//        } 
//        
//        // reverse array of chars
//        for (int j = 0; j < len; j++) {
//            charArray[j] =
//                tempCharArray[len - 1 - j];
//        }
//        
//        String reversePalindrome =
//            new String(charArray);
//        System.out.println(reversePalindrome);
//    }
}
