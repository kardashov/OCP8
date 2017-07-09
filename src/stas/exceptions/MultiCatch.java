package stas.exceptions;
	
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class MultiCatch {
	
	public static void main(String[] args) {
		try {
		Path path = Paths.get("dolphinsBorn.txt");
		String text = new String(Files.readAllBytes(path));
		LocalDate date = LocalDate.parse(text);
		System.out.println(date);
		} 
		catch (DateTimeParseException | IOException e) {  //MULTICATCH statement
			
//		catch(Exception1 e | Exception2 e | Exception3 e) // DOES NOT COMPILE
//		catch(Exception1 e1 | Exception2 e2 | Exception3 e3) // DOES NOT COMPILE
//		catch(Exception1 | Exception2 | Exception3 e) // correct MULTICATCH statement
				e.printStackTrace();
					throw new RuntimeException(e);
				} 
	
		try {
			throw new IOException();
		} catch (FileNotFoundException | IOException e) { // DOES NOT COMPILE
			
		} 
		// FileNotFoundException is a subclass of IOException . Specifying it in
		// the multi-catch is
		// redundant, and the compiler gives a message such as this:
		// The exception FileNotFoundException
		
	
//		This try statement is legal. It is a bad idea to reassign the variable 
//		in a catch block, but it is allowed:
		try {
			// do some work
		} catch (RuntimeException e) {
			e = new RuntimeException();
		}
		
		
//		When adding multi-catch, this pattern is no longer allowed:
//		Multi-catch Is Effectively Final
		try {
			throw new IOException();
		} catch (IOException | RuntimeException e) {
			e = new RuntimeException(); // DOES NOT COMPILE
			// The parameter e of a multi-catch block cannot be assigned
		}
		
		
}
}