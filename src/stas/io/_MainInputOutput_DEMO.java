package stas.io;

import java.io.*;

public class _MainInputOutput_DEMO {
	public static void main(String[] args) {

		{
			// Java offers two options to retrieve the local separator
			// character:
			System.out.println(System.getProperty("file.separator"));
			System.out.println(java.io.File.separator);
			System.out.println("============================ Using File class ===============================");

			File file = new File("/home/smith/data/zoo.txt"); // using absolute path
			System.out.println(file.exists());

			File parent = new File("/home/st");
			File child = new File(parent, "zoo.txt"); // using relative path
		}
		System.out.println("============================ Reading file/directory properties ==================");
		{
			File file = new File("//home//st//zoo.txt");
			System.out.println("File Exists: " + file.exists());
			if (file.exists()) { // true
				System.out.println("Absolute Path: " + file.getAbsolutePath());/// home/st/zoo.txt
				System.out.println("Is Directory: " + file.isDirectory()); // false
				System.out.println("Parent Path: " + file.getParent()); /// home/st
				if (file.isFile()) {
					System.out.println("File size: " + file.length()); // 334
					System.out.println("File LastModified: " + file.lastModified()); // 1498676504000
				} else { 
					for (File subfile : file.listFiles()) {
						System.out.println("\t" + subfile.getName()); 
					}
				}
			}

		}
		System.out.println("====================== Using streams of different levels ========");
		try (BufferedReader bufferedReader = 
				new BufferedReader(new FileReader("//home//st//zoo.txt"))) { //
			System.out.println(bufferedReader.readLine());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
/*		try (ObjectInputStream objectStream = new ObjectInputStream(
													new BufferedInputStream(
															new FileInputStream("//home//st//zoo2.txt")))) {
			System.out.println(objectStream.readObject());
		} catch (Exception e) {e.printStackTrace();	} */
//====================================================================================================
		System.out.println("====================== Using mark() ========================");
		
		try (BufferedReader bufferedReader = 
				new BufferedReader(new FileReader("//home//st//zoo.txt"))) { 
			if (bufferedReader.markSupported()) {
				bufferedReader.mark(100);//readAheadLimit Limit on the number of characters that may 
										 //be read while still preserving the mark.	
				System.out.println("Mark supported");
				System.out.print((char) bufferedReader.read());
				System.out.print((char) bufferedReader.read());
				bufferedReader.reset(); //reset back to marked position
			}
			
				System.out.print((char)bufferedReader.read());
				System.out.print((char)bufferedReader.read());
				System.out.print((char)bufferedReader.read());
			} catch (Exception e) {e.printStackTrace();}
		
		System.out.println("====================== Skipping over Data ========================");	
			
		try (BufferedReader is = new BufferedReader(new FileReader("//home//st//zoo.txt"))) {
			System.out.print((char) is.read());
			is.skip(2); ///
			is.read();
			System.out.print((char) is.read());
			System.out.print((char) is.read());

		} catch (Exception e) {e.printStackTrace();	}
		
		

	}//end of MAIN
}
