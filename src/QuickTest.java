import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.IntStream;


public class QuickTest {
	int i;
	public static void main(String[] args) {

     Comparator<String> nullsLast = Comparator.nullsLast(String::compareTo);
     
     List<String> list = new ArrayList<>(Arrays.asList("AA","BB", null, "CC" ));
     
     
     
     
     Collections.sort(list, nullsLast);
     System.out.println(list);
     
//     Collections.sort(list); // throws Runtime exceptions if nulls present in collections
//     System.out.println(list);
//     Predicate<Integer>  d = (Integer i)-> i%2==0;
//     Predicate even = d;
//     even.test("sdf");
		String sentence1 = "Carpe diem. Seize the day, boys. Make your lives extraordinary.";
		String sentence2 = "Frankly, my dear, I don't give a damn!";
		String sentence3 = "Do I look like I give a damn?";
		List<String> sentences = Arrays.asList(sentence1, sentence2, sentence3);

		Stream<String> strm = sentences.stream().flatMap(str -> Stream.of(str.split("[ ,.!?\r\n]")))
				.filter(s -> s.length() > 0).distinct();
		strm.forEach(System.out::println);
		
		System.out.println(Collectors.groupingBy(a -> a).characteristics());
		System.out.println(Collectors.groupingByConcurrent(a -> a).characteristics());
		System.out.println(Collectors.joining("_").characteristics());	
		System.out.println(Collectors.joining().characteristics());	
			
		int[] ccc = {3,4};
		IntStream cc = IntStream.of(ccc);
		cc.forEach(System.out::println);
	}
	
}
