import java.time.DayOfWeek;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ssdfs {

	public static void main(String[] args) {

		Stream<Integer> stream = Stream.generate(() -> (int) (Math.random() * 100000));

		long start = System.currentTimeMillis();

		// stream./* parallel().sequential().
		// */limit(500000000L).distinct().sorted().forEach(System.out::println);

		
		long end = System.currentTimeMillis();
		System.out.println((end - start) / 1000);
		
//		IntStream stream1 = IntStream.range(1, 500);
//		stream1.distinct().peek(System.out::print).limit(5).count();//12345

		/*
		 * stream.parallel().sorted().peek(c -> { if (c % 10000 == 0)
		 * System.out.println(c); }).count();
		 */
		System.out.print(Stream.iterate(1, x -> ++x).limit(5).map(x -> "" + x).collect(Collectors.
				joining("")));
		
		System.out.println(Duration.ofDays(3).ofHours(32));
		
		System.out.println("Iterating over array");
		Integer[] f = new Integer[] { 232, 1232, 323};
		Integer[] f2 = new Integer[] { 232, 1232, 323};
		int[] ff = { 34, 5, 2, 6, 6, 4, };
		
		Stream.of(ff).flatMap(x -> Stream.of(x)).sorted().forEach(System.out::println);;
//		Stream.of(f).sorted(Comparator.reverseOrder()).forEach(System.out::println);
//		Stream.of(f, f2).flatMap(x -> Stream.of(x)).sorted().forEach(System.out::println);
		
		System.out.println();
		m1(FF.VAL1);
	}

	static interface Enumer{}
	static enum FF implements Enumer{ VAL1, VAL2}
		
	static void m1(Enumer f) {
		if (f instanceof Enum<?>) {
		//	Enum<?> c = (Enum<?>) f;
		//	System.out.println(c);
			if (f == FF.VAL1)
				System.out.println("VAL1 is here");
			else
				return;

			}
		}
			
	}
	
	

