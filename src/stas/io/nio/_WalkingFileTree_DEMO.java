package stas.io.nio;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class _WalkingFileTree_DEMO {

	public static void main(String[] args) {
		System.out.println("=========================== Files.walk(path) =============================");
		
		Path path = Paths.get("/home");
		try {
			Files.walk(path).filter(p -> p.toString().endsWith(".java")).forEach(System.out::println);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("=========================Files.find()=============================================");
		System.out.println("== Files.find(Path start, int maxDepth,BiPredicate<Path, BasicFileAttributes> matcher ==");
		path = Paths.get("/home");
		long dateFilter = 1420070400000l;
		try {
			Stream<Path> stream = Files.find(path, 10,
					(p, a) -> p.toString().endsWith(".java") && a.lastModifiedTime().toMillis() > dateFilter);
			stream.forEach(System.out::println);
			
			
			stream = (Files.find(path, 0, (p, a) -> true));
			stream.forEach(System.out::println);// prints only  /home
		} catch (Exception e) {e.printStackTrace();}
		
		System.out.println("======================== Files.find(path) ==========================");
		try {
			 path = Paths.get("/home/st");
			Files.list(path)
			.filter(p -> !Files.isDirectory(p))
			.map(p -> p.toAbsolutePath())
			.forEach(System.out::println);
		} catch (IOException e) {e.printStackTrace();}
		
		System.out.println("======================== Files.lines(path) Printing File Contents ====================");
		
		path = Paths.get("/home/st/zoo.txt");
		try {
			Files.lines(path).forEach(System.out::println);
		} catch (IOException e) {e.printStackTrace();}
		
		
	}
}
