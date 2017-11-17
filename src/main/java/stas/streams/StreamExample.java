package stas.streams;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamExample {
	//
	// http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html
	//
	public static void main(String[] args) throws IOException {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

		List<Integer> twoEvenSquares = numbers.stream().filter(n -> {
			System.out.println("filtering " + n);
			return n % 2 == 0;
		}).map(n -> {
			System.out.println("mapping " + n);
			return n * n;
		}).collect(Collectors.toList());
		// .collect(Collectors.toCollection(ArrayList::new));

		// Генерируем int-stream (влючая граничные значения)
		IntStream oddNumbers = IntStream.rangeClosed(10, 30).filter(n -> n % 2 == 1);
		// Получаем стрим из набора элементов любого типа
		Stream<Integer> numbersFromValues = Stream.of(1, 2, 3, 4);

		// Получаем стрим из массива
		int[] numbers2 = { 1, 2, 3, 4 };
		IntStream numbersFromArray = Arrays.stream(numbers2);
		// You can also convert a file in a stream of lines using the
		// Files.lines static method. For example, in Listing 17 we count the
		// number of lines in a file.

		long numberOfLines = Files.lines(Paths.get("yourFile.txt"), Charset.defaultCharset()).count();

		// example that uses iterate to create a stream of all numbers that are
		// multiples of 10. The iterate method takes an initial value (here, 0)
		// and a lambda (of type UnaryOperator<T>) to apply successively on each
		// new value produced.
		Stream<Integer> numbers3 = Stream.iterate(0, n -> n + 10);
		
		// We can turn an infinite stream into a fixed-size stream using the
		// limit operation. For example, we can limit the size of the stream to
		// 5
		numbers3.limit(5).forEach(System.out::println); // 0, 10, 20, 30, 40

	}

}
