package stas.io.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class _NIO2_DEMO {

	public static void main(String[] args) throws URISyntaxException {
		System.out.println("================ Using Path Interface and Paths class ==============");

		Path path1 = Paths.get("pandas/cuddly.png"); 					// relative Unix path
		Path path2 = Paths.get("c:\\zooinfo\\November\\employees.txt"); // absolute windows path
		Path path3 = Paths.get("/home/zoodirector");					// Unix path

		path1 = Paths.get("pandas", "cuddly.png");
		path2 = Paths.get("c:", "zooinfo", "November", "employees.txt");
		path3 = Paths.get("/", "home", "zoodirector");

		try {
			path1 = Paths.get(new URI("file://pandas/cuddly.png"));// THROWS URISyntaxException
		} catch (Exception e) { 
			// URIs must reference absolute paths at runtime
			//e.printStackTrace();
		}
		path2 = Paths.get(new URI("file:///c:/zoo-info/November/employees.txt"));
		path3 = Paths.get(new URI("file:///home/zoodirectory"));

//		Path path4 = Paths.get(new URI("http://www.wiley.com"));
//		Path path5 = Paths.get(new URI("ftp://username:password@ftp.the-ftp-server.com"));

//		path4 = Paths.get(new URI("http://www.wiley.com"));
//		URI uri4 = path4.toUri(); // Convert Path back to URI

		Paths.get("/zoo/../home").getParent().normalize().toAbsolutePath();

		
		System.out.println("============================================================================");
		System.out.println("================ Using Path  toString(), getNameCount() and getName(int) ===");
		
		Path path = Paths.get("/land/hippo/harry.happy");
		System.out.println("The Path Name is: " + path);
		for (int i = 0; i < path.getNameCount(); i++) {
			System.out.println("Element " + i + " is: " + path.getName(i));
		}
//		Element 0 is: land			//ROOT element does not count.
//		Element 1 is: hippo			//element count starts from 0
//		Element 2 is: harry.happy			
										
		System.out.println("============================================================================");
		System.out.println("== Accessing Path Components with getFileName(), getParent() and getRoot() =");
		
		printPathInformation(Paths.get("/zoo/armadillo/shells.txt"));
		System.out.println();
		printPathInformation(Paths.get("armadillo/shells.txt"));

		System.out.println("============================================================================");
		System.out.println("=============== Path Type with isAbsolute() and toAbsolutePath() ===========");
		
			path1 = Paths.get("/birds/egret.txt");
		System.out.println("Path1 is Absolute? "+path1.isAbsolute()); //Path1 is Absolute? true
		System.out.println("Absolute Path1: "+path1.toAbsolutePath());//Absolute Path1: /birds/egret.txt
		
			path2 = Paths.get("../../birds/condor.txt");
		System.out.println("Path2 is Absolute? "+path2.isAbsolute());//Path2 is Absolute? false
		System.out.println("Absolute Path2 "+path2.toAbsolutePath());
						//	Absolute Path2 /home/st/workspace/ExamPreparation/../../birds/condor.txt
		
		System.out.println(Paths.get("/stripes/zebra.exe").isAbsolute());
		System.out.println(Paths.get("c:/goats/Food.java").isAbsolute());
//		Although the first line outputs true on a Linux or Mac-based system, it outputs false
//		on a Windows-based system since it is missing a drive letter prefix.
		
		System.out.println("=======================================================================");
		System.out.println("=============== subpath() Creating a New Path =========================");
		
			path = Paths.get("/mammal/carnivore/raccoon.image");
		System.out.println("Path is: "+path);
		System.out.println("Subpath from 0 to 3 is: "+path.subpath(0,3));
		System.out.println("Subpath from 1 to 3 is: "+path.subpath(1,3));
		System.out.println("Subpath from 1 to 2 is: "+path.subpath(1,2));
//		Path is: /mammal/carnivore/raccoon.image
//		Subpath from 0 to 3 is: mammal/carnivore/raccoon.image
//		Subpath from 1 to 3 is: carnivore/raccoon.image
//		Subpath from 1 to 2 is: carnivore
/*		
		System.out.println("Subpath from 0 to 4 is: "+path.subpath(0,4)); // THROWS EXCEPTION AT RUNTIME
		System.out.println("Subpath from 1 to 1 is: "+path.subpath(1,1)); // THROWS EXCEPTION AT RUNTIME*/
		
		System.out.println("======================================================================");
		System.out.println("=============== relativize() Deriving a Path =========================");
			path1 = Paths.get("fish.txt");
			path2 = Paths.get("birds.txt");
		System.out.println(path1.relativize(path2)); //     ../birds.txt
		System.out.println(path2.relativize(path1)); //		../fish.txt
		
			 path3 = Paths.get("E:\\habitat");
		Path path4 = Paths.get("E:\\sanctuary\\raven");
		System.out.println(path3.relativize(path4));//		..\sanctuary\raven
		System.out.println(path4.relativize(path3));//		..\..\habitat
//The relativize() method requires that BOTH paths be ABSOLUTE or BOTH RELATIVE
		
		path1 = Paths.get("/primate/chimpanzee");
		path2 = Paths.get("bananas.txt");
//		path1.relativize(path3); // THROWS EXCEPTION AT RUNTIME
		
/*		On Windows-based systems, it also requires that if absolute paths are used, then both
		paths must have the same root directory or drive letter. For example, the following would
		also throw an IllegalArgumentException at runtime in a Windows-based system since
		they use different roots:*/
		path3 = Paths.get("c:\\primate\\chimpanzee");
		path4 = Paths.get("d:\\storage\\bananas.txt");
//		path3.relativize(path4); // THROWS EXCEPTION AT RUNTIME
		
		System.out.println("======================================================================");
		System.out.println("=================  resolve() Joining Path Objects ====================");
		
		path1 = Paths.get("/cats/../panther");
		path2 = Paths.get("food");
		System.out.println(path1.resolve(path2));//       
		
		//      /cats/../panther/food     //
		/*
		 * resolve(Path) method for creating a new Path by joining an existing
		 * path to the current path. The object on which
		 * the resolve() method is invoked becomes the basis of the new Path
		 * object, with the input argument being appended onto the Path
		 */
			path1 = Paths.get("/turkey/food");
			path2 = Paths.get("/tiger/cage");
		System.out.println(path1.resolve(path2));//     /tiger/cage
/*		Since the input parameter path2 is an absolute path, the output would be the following:
		/tiger/cage     										*/
		
		System.out.println("==========================================================================");
		System.out.println("================= normalize() ============================================");
		path3 = Paths.get("E:\\data");
		path4 = Paths.get("E:\\mser\\home");
		Path relativePath = path3.relativize(path4);
		System.out.println(path3.resolve(relativePath));   // E:\data\..\mser\home
		/*this path value contains a redundancy. 
		We can resolve this redundancy by applying the normalize()*/
		System.out.println(path3.resolve(relativePath).normalize());
//		This modified last line of code nicely produces our original path value:
//			E:\mser\home
//		Like relativize() , the normalize() method does not check that the file actually exists
		
		System.out.println("==========================================================================");
		System.out.println("================= toRealPath() ===========================================");

//		Let’s say that we have a file system in which we have a symbolic link from food.source
//		to food.txt , as described in the following relationship:
//		/zebra/food.source → /horse/food.txt
//		Assuming that our current working directory is /horse/schedule , then consider the
//		following code:
		try {
			System.out.println(Paths.get("/zebra/food.source").toRealPath());
			System.out.println(Paths.get(".././food.txt").toRealPath());
		} catch (IOException e) {
		// Handle file I/O exception...
		}
//		Notice that we have to catch IOException , since unlike the toAbsolutePath() method,
//		the toRealPath() method interacts with the file system to check if the path is valid. Given the
//		symbolic link and current working directory as described, then the output would be the following:
//		/horse/food.txt
//		/horse/food.txt
		
		System.out.println("==========================================================================");
		System.out.println("================ Accessing the Underlying FileSystem Object ==============");
		/*
		 * Path.getPath() method is actually shortcut for the class
		 * java.nio.file.FileSystem method getPath(). The FileSystem class has a
		 * protected constructor, so we use the plural FileSystems factory class
		 * to obtain an instance of FileSystem
		 */

		path1 = FileSystems.getDefault().getPath("pandas/cuddly.png");
		path2 = FileSystems.getDefault().getPath("c:", "zooinfo", "November", "employees.txt");
		path3 = FileSystems.getDefault().getPath("/home/zoodirector");

//		FileSystem fileSystem = FileSystems.getFileSystem(new URI("http://www.selikoff.net"));
//		path = fileSystem.getPath("duck.txt");

		System.out.println("==========================================================================");
		System.out.println("================ Working with Legacy File Instances ======================");
		File file = new File("pandas/cuddly.png");
		path = file.toPath(); // convert to Path
		file = path.toFile(); // convert Path back to File
		
		System.out.println("==========================================================================");
		System.out.println("================ Files CLASS usage =======================================");
		System.out.println("==========================================================================");
		
		System.out.println("================  exists() Testing a Path ================================");
//		The Files.exists(Path) method takes a Path object and returns true if, and only if, it
//		references a file that exists in the file system.
		Files.exists(Paths.get("/ostrich/feathers.png"));
		Files.exists(Paths.get("/ostrich"));
		
		System.out.println("================ isSameFile(Path,Path) Testing Uniqueness ================");
//		The isSameFile() method first checks if the Path objects are equal in terms of equal() ,
//		and if so, it automatically returns true without checking to see if either file exists. If the
//		Path object equals() comparison returns false, then it locates each file to which the
//		path refers in the file system and determines if they are the same, throwing a checked
//		IOException if either file does not exist.
		try {
			out.println(Files.isSameFile(Paths.get("/user/home/cobra"), Paths.get("/user/home/snake")));
			out.println(Files.isSameFile(Paths.get("/user/tree/../monkey"), Paths.get("/user/monkey")));
			out.println(Files.isSameFile(Paths.get("/leaves/./giraffe.exe"), Paths.get("/leaves/giraffe.exe")));
			out.println(Files.isSameFile(Paths.get("/flamingo/tail.data"), Paths.get("/cardinal/tail.data")));
		} catch (IOException e) {
			out.println("Exception in isSameFile()...");
		}
		
		System.out.println("================ createDirectory() and createDirectories() ================");
		
		try {
			Files.createDirectory(Paths.get("/bison/field"));
//			The first example creates a new directory, field , in the directory /bison, assuming
//			/bison exists; or else an exception is thrown.
			Files.createDirectories(Paths.get("/bison/field/pasture/green"));
//			creates the directory green along 
//			with any of the following parent directories if they do not	already exist
		} catch (IOException e) {
			out.println("Exception in createDirectory() and createDirectories() ...");
		}
		
		System.out.println("================ copy() Duplicating File Contents or folder ===============");
		try {
			Files.copy(Paths.get("/home/st/zoo.txt"), Paths.get("/home/st/zooCopy.txt"),StandardCopyOption.REPLACE_EXISTING);
			Files.copy(Paths.get("/home/st/zoo.txt"), Paths.get("/home/st/zooCopy.txt"));//EXCEPTION target file exists
			Files.copy(Paths.get("/panda/bamboo.txt"), Paths.get("/panda-save/bamboo.txt"));
		} catch (IOException e) {out.print("Exception in copy()"); e.printStackTrace();}
		// by default, copying files and directories will traverse symbolic
		// links, although it will not overwrite a file or directory if it already exists,
		// nor will it copy file attributes.
		// These behaviors can be altered by providing the additional options
		// NOFOLLOW_LINKS, REPLACE_EXISTING, and COPY_ATTRIBUTES
		
		/* The NIO.2 Files API class contains two overloaded copy():
		 * The first copy(InputStream, Path) method reads the contents from the stream and writes
		 * the output to a file represented by a Path object.
		 * The second copy(Path, OutputStream) reads the contents of the file and writes the output to the stream.
		 */
		try (InputStream is = new FileInputStream("source-data.txt");
				OutputStream out = new FileOutputStream("output-data.txt")) {
			// Copy stream data to file
			Files.copy(is, Paths.get("c:\\mammals\\wolf.txt"));
			// Copy file data to stream
			Files.copy(Paths.get("c:\\fish\\clown.xsl"), out);
		} catch (IOException e) {}
		
		System.out.println("================ move() Changing a File Location ===============");
		try {
//			renames the zoo directory to zoo-new directory, keeping all of the
//			original contents from the source directory
			Files.move(Paths.get("c:\\zoo"), Paths.get("c:\\zoo-new"));
//			moves the addresses.txt file from the directory user to the directory 
//			zoo-new, and it renames it to addresses2.txt
			Files.move(Paths.get("c:\\user\\addresses.txt"),
			Paths.get("c:\\zoo-new\\addresses.txt"));
			} catch (IOException e) {e.printStackTrace();}
//		By default, the move() method will follow links, throw an exception if the file
//		already exists, and not perform an atomic move. These behaviors can be changed by
//		providing the optional values NOFOLLOW_LINKS, REPLACE_EXISTING, or ATOMIC_MOVE
//		If the file system does not support atomic moves, an AtomicMoveNotSupportedException will be thrown
		
		
		System.out.println("============ deleteIfExists() and delete() Removing a File =========");
//		The Files.delete(Path) method deletes a file or empty directory within the fi le system.
//			The delete() method throws the checked IOException under a variety of circumstances.
//		For example, if the path represents a non-empty directory, the operation will throw the
//		runtime DirectoryNotEmptyException. If the target of the path is a symbol link, then the
//		symbolic link will be deleted, not the target of the link.
//			The deleteIfExists(Path) method is identical to the delete(Path) method, except
//		that it will not throw an exception if the file or directory does not exist, but instead it will
//		return a boolean value of false. It will still throw an exception if the file or directory does
//		exist but fails, such as in the case of the directory not being empty.
		try {
			Files.delete(Paths.get("/vulture/feathers.txt"));
			Files.deleteIfExists(Paths.get("/pigeon"));
			} catch (IOException e) {e.printStackTrace();}
		
		System.out.println("======= Reading and Writing File Data with newBufferedReader() and newBufferedWriter() =========");
//		Files.newBufferedReader(Path,Charset) reads the file specified
//		at the Path location using a java.io.BufferedReader object. It also requires a Charset
//		value to determine what character encoding to use to read the file. You may remember
//		that we briefly discussed character encoding and Charset in Chapter 8.
		path = Paths.get("/animals/gopher.txt");
		try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("US-ASCII"))) {
			// Read from the stream
			String currentLine = null;
			while ((currentLine = reader.readLine()) != null)
				System.out.println(currentLine);
		} catch (IOException e) {	e.printStackTrace();	}
		
//		Files.newBufferedWriter(Path,Charset) writes to a file specified at
//		the Path location using a BufferedWriter. It also takes a Charset value:
		path = Paths.get("/animals/gorilla.txt");
		List<String> data = new ArrayList();
		try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-16"))) {
			writer.write("Hello World");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("=======  readAllLines() Reading Files =========");
		path = Paths.get("/home/st/zoo.txt");
		try {
			final List<String> lines = Files.readAllLines(path);
			for (String line : lines) {
				System.out.println(line);
			}
		} catch (IOException e) {e.printStackTrace();}
		
		
		
		
		
		
		
		
		
	}

	public static void printPathInformation(Path path) {
		System.out.println("Filename is: " + path.getFileName());
		System.out.println("Root is: " + path.getRoot());

		Path currentParent = path;
		while ((currentParent = currentParent.getParent()) != null) {
			System.out.println("Current parent is: " + currentParent);
		}
	}
}
