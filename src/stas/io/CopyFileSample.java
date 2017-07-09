package stas.io;
import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
/*The main() method creates two File objects, one for the source file to copy from and one
for the destination file to copy to. If the destination file already exists, it will be overridden by
this code. Both File objects are created using relative paths, so the application would search
for the Zoo.class in the current directory to read from, throwing a FileNotFoundException
if the file is not found, which is a subclass of an IOException.*/
public class CopyFileSample {
	public static void copy(File source, File destination) throws IOException {
		try (InputStream in = new FileInputStream(source); OutputStream out = new FileOutputStream(destination)) {

			int b;	
			while ((b = in.read()) != -1) {
				out.write(b);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		File source = new File("//home//st//zoo.txt");
		File destination = new File("//home//st//ZooCopy.txt");
//		copy(source, destination);
		//myCopy(source, destination);

//		myCopy(new File("//home//st//Calibre Library.zip"),
//				new File("//home//st//Calibre LibraryCopy.zip")); //Time taken: 1929seconds
		
		myCopyBuffered(new File("//home//st//Calibre Library.zip"),
				new File("//home//st//Calibre LibraryCopy.zip")); //Time taken: 34 seconds
		
		myCopyBufferedWithArray(new File("//home//st//Calibre Library.zip"),
				new File("//home//st//Calibre LibraryCopy.zip")); //Time taken: 8 seconds
	}

	public static void myCopy(File src, File dest) throws FileNotFoundException, IOException {
		
		LocalDateTime start = LocalDateTime.now();
		try (InputStream source = new FileInputStream(src);
				OutputStream destination = new FileOutputStream(dest)) {
			int buffer;
			while ((buffer = source.read()) != -1) {
				destination.write(buffer);
			}
		}
		
		System.out.print("Time taken: " + ChronoUnit.SECONDS.between(start, LocalDateTime.now()) + " seconds");
		System.out.println(" (" + ChronoUnit.MILLIS.between(start, LocalDateTime.now()) + " milliseconds)");	
		
	}
	
	public static void myCopyBuffered(File src, File dest) throws FileNotFoundException, IOException {
		FileWriter
		LocalDateTime start = LocalDateTime.now();
		try (InputStream source = new BufferedInputStream(new FileInputStream(src));
				OutputStream destination = new BufferedOutputStream(new FileOutputStream(dest))) {
			int buffer;
			while ((buffer = source.read()) != -1) {
				destination.write(buffer);
			}
		}
		
		System.out.print("Time taken: " + ChronoUnit.SECONDS.between(start, LocalDateTime.now()) + " seconds");
		System.out.println(" (" + ChronoUnit.MILLIS.between(start, LocalDateTime.now()) + " milliseconds)");	
		
	}
	
	public static void myCopyBufferedWithArray(File source, File destination) throws IOException {
		LocalDateTime start = LocalDateTime.now();
		try (InputStream in = new BufferedInputStream(new FileInputStream(source));
				OutputStream out = new BufferedOutputStream(

						new FileOutputStream(destination))) {
			byte[] buffer = new byte[1024];
			int lengthRead;
			while ((lengthRead = in.read(buffer)) > 0) {
				out.write(buffer, 0, lengthRead);
				out.flush();
			}
		}
		System.out.print("Time taken: " + ChronoUnit.SECONDS.between(start, LocalDateTime.now()) + " seconds");
		System.out.println(" (" + ChronoUnit.MILLIS.between(start, LocalDateTime.now()) + " milliseconds)");
	}
}