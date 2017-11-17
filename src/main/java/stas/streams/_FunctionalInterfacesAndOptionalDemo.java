package stas.streams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class _FunctionalInterfacesAndOptionalDemo {
	public static void main(String[] args) {

		System.out.println("================= Implementing Supplier ===================");
		Supplier<LocalDate> s1 = LocalDate::now;
		Supplier<LocalDate> s2 = () -> LocalDate.now();
		LocalDate d1 = s1.get();
		LocalDate d2 = s2.get();
		System.out.println(d1);
		System.out.println(d2);

		Supplier<StringBuilder> ss1 = StringBuilder::new;
		Supplier<StringBuilder> ss2 = () -> new StringBuilder();
		System.out.println(ss1.get());
		System.out.println(ss2.get());
		
		Supplier<ArrayList<String>> sss1 = ArrayList<String>::new;
		ArrayList<String> a1 = sss1.get();
		System.out.println(a1);//[]
		System.out.println(s1);//stas.streams._MainStreamDemo$$Lambda$1/1915318863@3f91beef
/*		Our test class is named _MainStreamDemo , and it is in a package that we created named
		stas.streams. Then comes $$ , which means that the class doesn’t exist in a class
		file on the file system. It exists only in memory.*/
		
		System.out.println("================= Implementing Consumer and BiConsumer ====");
/*		@FunctionalInterface public class Consumer<T> {
			void accept(T t);
			}
		@FunctionalInterface public class BiConsumer<T, U> {
			void accept(T t, U u);
			}*/
		Consumer<String> c1 = System.out::println;
		Consumer<String> c2 = x -> System.out.println(x);
		Consumer<String> c3 = new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		};
		c1.accept("Annie");
		c2.accept("Annie");
		c3.accept("Stan");
		
		Map<String, Integer> map = new HashMap<>();
		BiConsumer<String, Integer> b1 = map::put;
		BiConsumer<String, Integer> b2 = (k, v) -> map.put(k, v);
		b1.accept("chicken", 7);
		b2.accept("chick", 1);
		System.out.println(map);//{chicken=7, chick=1}
		
		Map<String, String> map1 = new HashMap<>();
		BiConsumer<String, String> b11 = map1::put;
		BiConsumer<String, String> b21 = (k, v) -> map1.put(k, v);
		b11.accept("chicken", "Cluck");
		b21.accept("chick", "Tweep");
		System.out.println(map1);//{chicken=Cluck, chick=Tweep}
		
		
		System.out.println("================= Implementing Predicate and BiPredicate =====");
		/*@FunctionalInterface public class Predicate<T> {
			boolean test(T t);
			}
		@FunctionalInterface public class BiPredicate<T, U> {
			boolean test(T t, U u);
			}*/
		Predicate<String> p1 = String::isEmpty;
		Predicate<String> p2 = x -> x.isEmpty();
		System.out.println(p1.test(""));
		System.out.println(p2.test(""));
		
		BiPredicate<String, String> b111 = String::startsWith;
//=============================================================================================
//=======================Using INSTANCE METHOD references======================================
		/*startsWith()
		is an instance method. This means that the first parameter in the lambda is used
		as the instance on which to call the method. The second parameter is passed to the
		startsWith() method itself.*/
//=============================================================================================		
		BiPredicate<String, String> b211 = (string, prefix) -> string.startsWith(prefix);
		System.out.println(b111.test("chicken", "chick"));
		System.out.println(b211.test("chicken", "chick"));
				
		System.out.println("================= Default Methods on Functional Interfaces ===");
		
		Predicate<String> egg = s -> s.contains("egg");
		Predicate<String> brown = s -> s.contains("brown");
		
		Predicate<String> brownEggs = egg.and(brown);
		Predicate<String> otherEggs = egg.and(brown.negate());
		
		System.out.println("================= Implementing Function and BiFunction =======");
		/*
		 * @FunctionalInterface public class Function<T, R> { R apply(T t); }
		 * 
		 * @FunctionalInterface public class BiFunction<T, U, R> { R apply(T t,
		 * U u); }
		 */
		
		Function<String, Integer> f1 = String::length;
		Function<String, Integer> f2 = x -> x.length();
		System.out.println(f1.apply("cluck"));// 5
		System.out.println(f2.apply("cluck"));// 5
		
		BiFunction<String, String, String> b33 = String::concat;
		BiFunction<String, String, String> b22 = (string, toAdd) -> string.concat(toAdd);
		System.out.println(b33.apply("baby ", "chick")); // baby chick
		System.out.println(b22.apply("baby ", "chick")); // baby chick
		
		System.out.println("================= Implementing UnaryOperator and BinaryOperator ==");
		/*
		 * @FunctionalInterface public class UnaryOperator<T> extends
		 * Function<T, T> { }
		 * 
		 * @FunctionalInterface public class BinaryOperator<T> extends
		 * BiFunction<T, T, T> { }
		 */
		UnaryOperator<String> u1 = String::toUpperCase;
		UnaryOperator<String> u2 = x -> x.toUpperCase();
		
		BinaryOperator<String> b345 = String::concat;
		BinaryOperator<String> b3454 = (string, toAdd) -> string.concat(toAdd);
		
		System.out.println(b345.apply("baby ", "chick")); // baby chick
		System.out.println(b3454.apply("baby ", "chick")); // baby chick
		
		System.out.println("================= Returning an Optional ==========================");
		
		System.out.println(average(90, 100)); // Optional[95.0]
		System.out.println(average()); // Optional.empty
		
//		average().get();// throws NoSuchElementException: No value present
		
		Optional<Double> opt = average(90, 100);
		if (opt.isPresent())
			System.out.println(opt.get()); // 95.0
		
		opt.ifPresent(System.out::println);// 95.0   another method of writing
		
		opt = average();
		System.out.println(opt.orElse(Double.NaN));
		System.out.println(opt.orElseGet(() -> Math.random()));
//		System.out.println(opt.orElseThrow(() -> new IllegalStateException())); //exception is thrown
		
		opt = average(90, 100);
		System.out.println(opt.orElse(Double.NaN));
		System.out.println(opt.orElseGet(() -> Math.random()));
		System.out.println(opt.orElseThrow(() -> new IllegalStateException()));//exception is NOT thrown
		
		System.out.println("================= Chaining Optionals ===========================");
//		print the value of Optional<Integer>, only if it is a three-digit number.
		Optional<Integer> optional1 = Optional.of(544);
		optional1.map(n -> "" + n) 								// part 1
				.filter(s -> s.length() == 3) 					// part 2
				.ifPresent(System.out::println);//prints 544    // part 3
	}

	
	public static Optional<Double> average(int... scores) {
		if (scores.length == 0)	return Optional.empty();
		
		int sum = 0;
		for (int score : scores) sum += score;
		return Optional.of((double) sum / scores.length);
	}}





