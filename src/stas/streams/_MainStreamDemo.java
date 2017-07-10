package stas.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.*;

public class _MainStreamDemo {

	public static void main(String[] args) {

		Stream<String> empty = Stream.empty();// count = 0
		Stream<Integer> singleElement = Stream.of(1);// count = 1
		Stream<Integer> fromArray = Stream.of(1, 2, 3);// count = 2

		List<String> list = Arrays.asList("a", "b", "c");
		Stream<String> fromList = list.stream();
		Stream<String> fromListParallel = list.parallelStream();
		
		Stream<Double> randoms = Stream.generate(Math::random);//Stream of randoms
		Optional<Double> sfsdf = randoms.limit(500).max(Double::compare);
		
		Stream<Double> constants = Stream.generate(() -> 2.0); ////Stream of constants
		Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);
		
		int[] ff = { 34, 5, 2, 6, 6, 4, }; 					 
		Arrays.stream(ff).sorted().forEach(System.out::print); //Stream of array of primitives
		
		System.out.println("=================== Stream TERMINAL OPERATIONS ===============");
		System.out.println("=================== COUNT() ==================================");
//		count() method determines the number of elements in a finite stream
		Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
		System.out.println(s.count());// 3
		
		System.out.println();
		System.out.println("=================== MIN() and MAX() ==========================");
//		min() and max() methods allow you to pass a custom comparator and find the smallest
//		or largest value in a finite stream according to that sort order.
//			Optional<T> min(<? super T> comparator)
//			Optional<T> max(<? super T> comparator)
				s = Stream.of("monkey", "ape", "bonobo");
		Optional<String> min = s.min((s1, s2) -> s1.length() - s2.length());
		min.ifPresent(System.out::println); // ape     //animal with fewest letters in its name
		
		//Empty of stream
		Optional<?> minEmpty = Stream.empty().min((s1, s2) -> 0);
		System.out.println(minEmpty.isPresent());// false
		
		System.out.println();
		System.out.println("================== findAny() and findFirst() =================");
//		The findAny() and findFirst() methods return an element of the stream unless the stream
//		is empty. If the stream is empty, they return an empty Optional.
//		Optional<T> findAny()
//		Optional<T> findFirst()
					s = Stream.of("monkey", "gorilla", "bonobo");
		Stream<String> infinite = Stream.generate(() -> Math.random() > .5?"chimp":"stas");
		s.findAny().ifPresent(System.out::println); // monkey
		infinite.findAny().ifPresent(System.out::println); // chimp
		
		System.out.println();
		System.out.println("================== allMatch(), anyMatch() and noneMatch() ====");
//		The allMatch() , anyMatch() and noneMatch() methods search a stream and return information 
//		about how the stream pertains to the predicate
//		boolean anyMatch(Predicate <? super T> predicate)
//		boolean allMatch(Predicate <? super T> predicate)
//		boolean noneMatch(Predicate <? super T> predicate)
		list = Arrays.asList("monkey", "2", "chimp");
		infinite = Stream.generate(() -> "chimp");
		
		Predicate<String> pred = x -> Character.isLetter(x.charAt(0));
		
		System.out.println(list.stream().anyMatch(pred)); // true
		System.out.println(list.stream().allMatch(pred)); // false
		System.out.println(list.stream().noneMatch(pred)); //false
		System.out.println(infinite.anyMatch(pred)); // true
		
		System.out.println();
		System.out.println("================== FOREACH() =================================");
//		void forEach(Consumer<? super T> action)
//		DOES NOT TERMINATE INFINITE STREAM
		
		s = Stream.of("Monkey", "Gorilla", "Bonobo");
		s.forEach(System.out::print);// MonkeyGorillaBonobo
		
		
		System.out.println();
		System.out.println("================== REDUCE() ==================================");
//		reduce() method combines a stream into a single object
	/*	T           reduce(T identity, BinaryOperator<T> accumulator)
		Optional<T> reduce(BinaryOperator<T> accumulator)
		<U> U       reduce(U identity, BiFunction<U,? super T,U> accumulator,	
												BinaryOperator<U> combiner)*/
		/*The most common way of doing a reduction is to start
		with an initial value and keep merging it with the next value. Think about how you would
		concatenate an array of String s into a single String without functional programming. It
		might look something like this:*/
		String[] array = new String[] { "w", "o", "l", "f" };
		String result = "";
		for (String s1 : array)
			result = result + s1;
		System.out.println(result);
	/*	The initial value of an empty String is the IDENTITY. The accumulator combines the cur-
		rent result with the current String . With lambdas, we can do the same thing with a stream
		and reduction:*/
		
		Stream<String> stream = Stream.of("w", "o", "l", "f");
		String word = stream.reduce("", (s1, c) -> s1 + c);
		System.out.println(word);// wolf
		
//		We can rewrite this with a method reference:
		stream = Stream.of("w", "o", "l", "f"); 
		word = stream.reduce("", String::concat);
		System.out.println(word);// wolf
		
//		reduction to multiply all of the Integer objects in a stream
		Stream<Integer> stream1 = Stream.of(3, 5, 6);
		System.out.println(stream1.reduce(1, (a, b) -> a*b));
		
		
		/*We set the identity to 1 and the accumulator to multiplication. In many cases, the identity 
		 isn’t really necessary, so Java lets us omit it. When you don’t specify an identity, an
		Optional is returned because there might not be any data. There are three choices for what
		is in the Optional :
		---If the stream is empty, an empty Optional is returned.
		---If the stream has one element, it is returned.
		---If the stream has multiple elements, the accumulator is applied to combine them.
		The following illustrates each of these scenarios:*/
		BinaryOperator<Integer> op = (a, b) -> a * b;
		Stream<Integer> empty1 = Stream.empty();
		Stream<Integer> oneElement = Stream.of(3);			//stream with single element
		Stream<Integer> threeElements = Stream.of(3, 5, 6);
		
		empty1.reduce(op).ifPresent(System.out::println); // no output      for empty stream
		oneElement.reduce(op).ifPresent(System.out::println); // 3		  for stream with one element
		threeElements.reduce(op).ifPresent(System.out::println); // 90	  
		
		/*The third method signature is used when we are processing collections in parallel. It
		allows Java to create intermediate reductions and then combine them at the end. In our
		example, it looks similar. While we aren’t actually using a parallel stream here, Java
		assumes that a stream might be parallel. This is helpful because it lets us switch to
		a parallel stream easily in the future:*/
		Stream<Integer> stream3 = Stream.of(3, 5, 6);
		System.out.println(stream3.reduce(1, op, op)); // 90
		
		System.out.println();
		System.out.println("================ COLLECT() ==================================");
		/*	
		<R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator,
				BiConsumer<R, R> combiner)
		<R,A> R collect(Collector<? super T, A,R> collector)*/
			 stream = Stream.of("w", "o", "l", "f");

		StringBuilder word1 = stream.collect(StringBuilder::new, StringBuilder::append, 
															StringBuilder::append);
	/*		The first parameter is a Supplier that creates the object that will store the results as we
		collect data. In this case, it constructs a new StringBuilder .
			The second parameter is a BiConsumer, which takes two parameters and doesn’t return
		anything. It is responsible for adding one more element to the data collection. In this 
		example, it appends the next String to the StringBuilder.
			The final parameter is another BiConsumer . It is responsible for taking two data 
		collections and merging them. This is useful when we are processing in parallel. Two smaller
		collections are formed and then merged into one. This would work with StringBuilder
		only if we didn’t care about the order of the letters. In this case, the accumulator and
		combiner have similar logic.*/
		
		stream = Stream.of("w", "o", "l", "f");
		TreeSet<String> set = stream.collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
		System.out.println(set); // [f, l, o, w]
		/*The collector has three parts as before. 
		 * The SUPPLIER creates an empty TreeSet. 
		 * The ACCUMULATOR adds a single String from the Stream to the TreeSet. 
		 * The COMBINER adds all of the elements of one TreeSet to another in case 
		 * the operations were done in parallel and	need to be merged.*/
		
		
		stream = Stream.of("w", "o", "l", "f");
		set = stream.collect(Collectors.toCollection(TreeSet::new));
		System.out.println(set); // [f, l, o, w]
		// If we didn’t need the set to be sorted, we could make the code even
		// shorter:
		stream = Stream.of("w", "o", "l", "f");
		Set<String> set1 = stream.collect(Collectors.toSet());
		System.out.println(set1); // [f, w, l, o]
		
		System.out.println("================== toARRAY() ======================================");
		stream = Stream.of("w", "o", "l", "f");
		Object[] objarr = stream.toArray();
		System.out.println(Arrays.toString(objarr));// [w, o, l, f]
		
		stream = Stream.of("w", "o", "l", "f");
		String[] str = stream.toArray(x -> new String[x]);
		System.out.println(Arrays.toString(str));   // [w, o, l, f]
		
		
		System.out.println("===================Stream INTERMIDIATE OPERATIONS==================");
		
		System.out.println("===================FILTER()========================================");
//		Stream<T> filter(Predicate<? super T> predicate)
		s = Stream.of("monkey", "gorilla", "bonobo");
		s.filter(x -> x.startsWith("m")).forEach(System.out::print);// monkey
		
		System.out.println();
		System.out.println("===================DISTINCT()======================================");
//		distinct() method returns a stream with duplicate values removed. 
//		The duplicates do not need to be adjacent to be removed
		s = Stream.of("duck", "duck", "duck", "goose");
		s.distinct().forEach(System.out::print); // duckgoose
		
		System.out.println();
		System.out.println("===================LIMIT() and SKIP()==============================");
/*		The limit() and skip() could make a finite stream smaller, 
 * 		or they could make a finite stream out of an infinite stream.
			Stream<T> limit(int maxSize)
			Stream<T> skip(int n)*/
		Stream<Integer> sInt = Stream.iterate(1, n -> n + 1);
		sInt.skip(5).limit(2).forEach(System.out::print);// 67
		
		System.out.println();
		System.out.println("===================MAP()===========================================");
		/*map() method creates a one-to-one mapping from the elements in 
		 * the stream to the elements of the next step in the stream. 
				<R> Stream<R> map(Function<? super T, ? extends R> mapper)*/
		s = Stream.of("monkey", "gorilla", "bonobo");
		s.map(String::length).forEach(System.out::print);// 676
		
		System.out.println();
		System.out.println("===================flatMap()=======================================");
		/*The flatMap() method takes each element in the stream and makes any elements it
		 contains top-level elements in a single stream. This is helpful when you want to remove empty
		elements from a stream or you want to combine a stream of lists.
			<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)*/
		List<String> zero = Arrays.asList();
		List<String> one = Arrays.asList("Bonobo");
		List<String> two = Arrays.asList("Mama Gorilla", "Baby Gorilla");
		Stream<List<String>> animals = Stream.of(zero, one, two);
		animals/*.flatMap(l -> l.stream())*/.forEach(System.out::println);
//				[]                 --result without flatMap
//				[Bonobo]
//				[Mama Gorilla, Baby Gorilla]
		animals = Stream.of(zero, one, two);
		animals.flatMap(l -> l.stream()).forEach(System.out::println);
//				Bonobo
//				Mama Gorilla
//				Baby Gorilla
		
		Stream<String> zero1 = Stream.empty();
		Stream<String> one1 = Stream.of("Bonobo");
		Stream<String> two1 = Stream.of("Mama Gorilla", "Baby Gorilla");
		Stream.of(zero1, one1, two1).flatMap(t -> t).forEach(System.out::println);
//				Bonobo
//				Mama Gorilla
//				Baby Gorilla
		
		System.out.println();
		System.out.println("====================SORTED()======================================");
/*		The sorted() method returns a stream with the elements sorted. Just like sorting arrays,
		Java uses natural ordering unless we specify a comparator. 
			Stream<T> sorted()
			Stream<T> sorted(Comparator<? super T> comparator)*/
		s = Stream.of("brown-", "bear-");
		s.sorted().forEach(System.out::print); // bear-brown-
		
		s = Stream.of("brown bear-", "grizzly-");
		s.sorted(Comparator.reverseOrder()).forEach(System.out::print); // grizzly-brown bear-
//		s.sorted(Comparator::reverseOrder); // DOES NOT COMPILE
		
		System.out.println();
		System.out.println("=================== PEEK() ======================================");
/*		peek() method is useful for debugging because it allows us 
 * 		to perform a stream operation without actually changing the stream.
			Stream<T> peek(Consumer<? super T> action)			*/
		
		stream = Stream.of("black bear", "brown bear", "grizzly");
		long count = stream.filter(ss -> ss.startsWith("g"))
						   .peek(System.out::println).count();// grizzly
		System.out.println(count);// 1
/////////////////////////////////////////////////////////////////////////////////		
//		peek() MUST NOT modify the data structure that is used in the stream
/////////////////////////////////////////////////////////////////////////////////		
		
		System.out.println("=================== Putting Together the Pipeline ===============");
		list = Arrays.asList("Toby", "Anna", "Leroy", "Alex");
		list.stream()
			.filter(n -> n.length() == 4)
			.sorted()
			.limit(2)							
			.forEach(System.out::println);    //Alex Anna
		
		
		Stream<Integer> infinite1 = Stream.iterate(1, x -> x + 1);
		infinite1.limit(5)
			.peek(System.out::print)
			.filter(x -> x % 2 == 1)
			.forEach(System.out::print); //11233455
		/*As the first element passes through, 1 shows up in the
		peek() and print() . The second element makes it past the limit() and peek() , but it
		gets caught in the filter() . The third and fifth elements behave like the first element.
		The fourth behaves like the second.*/
		
		infinite1 = Stream.iterate(1, x -> x + 1);
		infinite1.filter(x -> x % 2 == 1)
				.limit(5)
				.forEach(System.out::print); // 13579
		
		infinite1 = Stream.iterate(1, x -> x + 1);
		infinite1.filter(x -> x % 2 == 1)
				.peek(System.out::print)
				.limit(5)
				.forEach(System.out::print);// 1133557799
									//Since filter() is before peek() , we see only the odd numbers.
		
		
		System.out.println("=================== Linking Streams to the Underlying Data ============");
		
		List<String> cats = new ArrayList<>();
		cats.add("Annie");
		cats.add("Ripley");
		
		stream = cats.stream();
		cats.add("KC");
		System.out.println(stream.count()); //3    streams are lazily evaluated
		
		
	}
}
