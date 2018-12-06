package stas.misc;

// A simple program to accept an integer from user

import java.util.NoSuchElementException;
import java.util.Scanner;

class ScanInt1 {
    public static void main(String[] args) {
        String integerStr = "";
        System.out.println("The string to scan integer from it is: "
                + integerStr);
        Scanner consoleScanner = new Scanner(integerStr);
        try {

            System.out.println("The integer value scanned from string is: "
                    + consoleScanner.nextInt());
        }
//		catch (InputMismatchException ime) {
//			System.out
//					.println("Error: Cannot scan an integer from the given string");
//		}

        catch (NoSuchElementException nsee) {
            System.out
                    .println("Error: Cannot scan an integer from the given string");

        } catch (IllegalStateException ise) {
            System.out
                    .println("Error: nextInt() called on a closed Scanner object");

        } catch (Throwable nsee) {
            System.out
                    .println("Error: Cannot scan an integer from the given string");

        }
    }
}
