package stas.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _Reduce_Demo {
    public static void main(String[] args) {
        List<String> vals = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

        long start = System.currentTimeMillis();
        String join = Stream.generate(() -> vals).limit(500).flatMap(x -> x.stream()).parallel()
                .peek(System.out::println)
                /*
                 * this shows how the elements are retrieved from the stream
                 */
                .reduce("_", (a, b) -> {
                    System.out.println("reducing " + a + " and " + b + " Thread: " + Thread.currentThread().getName());
                    return a.concat(b);
                }, (a, b) -> {
                    System.out.println("combining " + a + " and " + b + " Thread: " + Thread.currentThread().getName());
                    return a.concat(b);
                });
        System.out.println(join);

        System.out.println("Time: " + (System.currentTimeMillis() - (double) start) / 1000 + " seconds");

        join = Stream.generate(() -> vals).limit(500).flatMap(x -> x.stream()).parallel()
                .peek(System.out::println)
                /*
                 * this shows how the elements are retrieved from the stream
                 */
                .collect(Collectors.joining("_"));
        System.out.println(join);
    }
}
