package stas.streams;

import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class _PrimitiveStreams {

    public static void main(String[] args) {

        System.out.println("================ Working with PRIMITIVE STREAMS ===================");

        Stream<Integer> stream = Stream.of(1, 2, 3);
        System.out.println(stream.reduce(0, (s, n) -> s + n));

        // Another way of doing that
        stream = Stream.of(1, 2, 3);
        System.out.println(stream.mapToInt(x -> x).sum());

        IntStream intStream = IntStream.of(1, 2, 3);
        OptionalDouble avg = intStream.average();
        System.out.println(avg.getAsDouble());// 2

        // IntStream : Used for the primitive types int, short, byte, and char
        // LongStream : Used for the primitive type long
        // DoubleStream : Used for the primitive types double and float

        System.out.println("================ Creating PRIMITIVE STREAMS =======================");
        DoubleStream empty = DoubleStream.empty();
        DoubleStream oneValue = DoubleStream.of(3.14);
        DoubleStream varargs = DoubleStream.of(1.0, 1.1, 1.2);
        DoubleStream random = DoubleStream.generate(Math::random);
        DoubleStream fractions = DoubleStream.iterate(.5, d -> d / 2);
        DoubleStream concat = DoubleStream.concat(oneValue, varargs);

        IntStream range = IntStream.range(1, 6);//the numbers 1�6, NOT INCLUDING number 6
        IntStream rangeClosed = IntStream.rangeClosed(1, 5); //INCLUDING number 5

        Stream<String> objStream = Stream.of("penguin", "fish");
        intStream = objStream.mapToInt(s -> s.length());

        System.out.println("================ Optional with Primitive Streams ==================");
        IntStream stream1 = IntStream.rangeClosed(1, 10);
        OptionalDouble optional = stream1.average();

        optional.ifPresent(System.out::println);
        System.out.println(optional.getAsDouble());
        System.out.println(optional.orElseGet(() -> Double.NaN));

        LongStream longs = LongStream.of(5, 10);
        long sum = longs.sum();
        System.out.println(sum); // 15

        DoubleStream doubles = DoubleStream.generate(() -> Math.PI);
        LongStream.range(1, 1000);
//		OptionalDouble min = doubles.min(); // runs infinitely


        System.out.println("================ Summarizing Statistics ==============");

        IntStream ints = IntStream.range(1, 400);
        IntSummaryStatistics stats = ints.summaryStatistics();
        DoubleSummaryStatistics d;
        System.out.println("getMax())   = " + stats.getMax());     //getMax());
        System.out.println("getMin())   = " + stats.getMin());     //getMin());
        System.out.println("getCount()) = " + stats.getCount());   //getCount());
        System.out.println("getAverage()= " + stats.getAverage()); //getAverage()
        System.out.println("getSum())   = " + stats.getSum());     //getSum());


    }

}

