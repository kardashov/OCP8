package stas.misc;

public class ValueOfDemo {
    public static void main(String[] args) {

        // this program requires two 
        // arguments on the command line 
        if (args.length > 1) {

            long sum = 0;
            // convert strings to numbers

            for (String i : args) {
                sum = sum + Integer.valueOf(i);
            }
//        	System.out.println( Integer.toBinaryString(666));
            System.out.println("The sum of args is: " + sum);


        } else {
            System.out.println("This program " +
                    "requires two command-line arguments.");
        }
    }
}