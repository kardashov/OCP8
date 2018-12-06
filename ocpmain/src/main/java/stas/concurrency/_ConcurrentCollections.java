package stas.concurrency;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class _ConcurrentCollections {
    public static void main(String[] args) {
        System.out.println("======================Concurrent Collections====================");
        ConcurrentMap<String, Integer> chm = new ConcurrentHashMap<>();
        Deque<String> cld = new ConcurrentLinkedDeque<>();
        Queue<String> clq = new ConcurrentLinkedQueue<>();
//	https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html
        ConcurrentMap<String, Integer> cslm1 = new ConcurrentSkipListMap<>();
        SortedMap<String, Integer> cslm2 = new ConcurrentSkipListMap<>();
        NavigableMap<String, Integer> cslm3 = new ConcurrentSkipListMap<>();

        SortedSet<String> csls = new ConcurrentSkipListSet<>();
        NavigableSet<String> csls1 = new ConcurrentSkipListSet<>();

        List<String> cowal = new CopyOnWriteArrayList<>();
        Set<String> cowas = new CopyOnWriteArraySet<>();

        BlockingQueue<String> lbd1 = new LinkedBlockingDeque<>();
        BlockingDeque<String> lbd2 = new LinkedBlockingDeque<>();
//	https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html
        BlockingQueue<String> lbd3 = new LinkedBlockingQueue<>();
//	https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingQueue.html

        System.out.println("=======================Synchronized Collections===========================");
//	Collections.synchronizedCollection(Collection<T> c)
//	Collections.synchronizedList(List<T> list)

//	Collections.synchronizedMap(Map<K,V> m)
//	Collections.synchronizedSortedMap(SortedMap<K,V> m)
//	Collections.synchronizedNavigableMap(NavigableMap<K,V> m)

//	Collections.synchronizedSet(Set<T> s)
//	Collections.synchronizedSortedSet(SortedSet<T> s)
//	Collections.synchronizedNavigableSet(NavigableSet<T> s)


        Map<String, Object> foodData = new HashMap<String, Object>();
        foodData.put("penguin", 1);
        foodData.put("flamingo", 2);

//	for (String key : foodData.keySet())
//		foodData.remove(key); //Throws ConcurrentModificationException !!!!  FailFast iterator

        foodData = new ConcurrentHashMap<String, Object>();
        foodData.put("penguin", 1);
        foodData.put("flamingo", 2);
        for (String key : foodData.keySet())
            foodData.remove(key); //But here works fine!!!
        System.out.println(foodData);


        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("zebra", 52);
        map.put("elephant", 10);
        System.out.println(map.get("elephant"));

        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.offer(31);
//	queue.offer(null); //Throws  java.lang.NullPointerException
        System.out.println(queue.peek());
        System.out.println(queue.poll());

        Deque<Integer> deque = new ConcurrentLinkedDeque<>();
        deque.offer(10);
        deque.push(4);
        System.out.println(deque.peek());
        System.out.println(deque.pop());

        System.out.println("=======================LinkedBlockingQueue===========================");
        try {
            BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
//			blockingQueue.offer(39);
//			blockingQueue.offer(3, 4, TimeUnit.SECONDS);
            System.out.println(blockingQueue.poll());
            System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            // Handle interruption
        }

        System.out.println("=======================LinkedBlockingDeque===========================");
        try {
            BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>();
            blockingDeque.offer(91);
            blockingDeque.offerFirst(5, 2, TimeUnit.MINUTES);
            blockingDeque.offerLast(47, 100, TimeUnit.MICROSECONDS);
            blockingDeque.offer(3, 4, TimeUnit.SECONDS);
            System.out.println(blockingDeque.poll());
            System.out.println(blockingDeque.poll(950, TimeUnit.MILLISECONDS));
            System.out.println(blockingDeque.pollFirst(200, TimeUnit.NANOSECONDS));
            System.out.println(blockingDeque.pollLast(1, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            // Handle interruption
        }
        System.out.println("=======================CopyOnWriteArrayList===========================");
        List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(4, 3, 52));
        for (Integer item : list) {
            System.out.print(item + " ");
            list.add(9);
        }
        System.out.println();
        System.out.println("Size: " + list.size());
        System.out.println(list);
/*		Despite adding elements to the array while iterating over it, only those elements in the
		collection at the time the for() loop was created were accessed. Alternatively, if we had
		used a regular ArrayList object, a ConcurrentModificationException would have been
		thrown at runtime.*/
        System.out.println("=======================Parallel Streams===========================");

        //Two ways of creation of parallel stream:  parallel() and parallelStream()
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        Stream<Integer> parallelStream = stream.parallel(); //parallel()
        Stream<Integer> parallelStream2 = Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream();//parallelStream()

        Arrays.asList(1, 2, 3, 4, 5, 6).stream().forEach(s -> System.out.print(s + " "));
        Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().forEach(s -> System.out.print(s + " "));
        Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().forEachOrdered(s -> System.out.print(s + " "));

//		Arrays.asList("jackal", "kangaroo", "lemur").parallelStream().map(s -> s.toUpperCase())
//				.forEach(System.out::println);

        Arrays.asList("jackal", "kangaroo", "lemur").parallelStream().map(s -> {
            System.out.println(s);
            return s.toUpperCase();
        }).forEach(System.out::println);

        System.out.println("========================Avoiding Stateful Operations=============================");
        List<Integer> data = Collections.synchronizedList(new ArrayList<>());//using synchronized List
        Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().map(i -> {
            data.add(i);
            return i;// AVOID STATEFUL LAMBDA EXPRESSIONS!
        })
                .forEachOrdered(i -> System.out.print(i + " "));//ALWAYS prints 1 2 3 4 5 6

        System.out.println();
        for (Integer e : data) {
            System.out.print(e + " "); // but elements in collection may be in any random order
        }

        System.out.println("========================Parallel Reductions=============================");
        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6).stream().findAny().get());// always prints 1
        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().findAny().get());//random result 1...6

        System.out.println(Arrays.asList('w', 'o', 'l', 'f')
                .stream()
                .reduce("", (c, s1) -> c + s1,
                        (s2, s3) -> s2 + s3)); // prints   wolf
		
	/*	Requirements for parallel stream reduce() Arguments
			The identity must be defined such that for all elements in the stream u ,
		combiner.apply(identity, u) is equal to u .
			The accumulator operator op must be associative and stateless such that (a op b) op c
		is equal to a op (b op c) .
			The combiner operator must also be associative and stateless and compatible with the
		identity, such that for all u and t combiner.apply(u,accumulator.apply(identity,t))
		is equal to accumulator.apply(u,t).*/

        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6)
                .parallelStream()
                .reduce(0, (a, b) -> (a - b))); // NOT AN ASSOCIATIVE ACCUMULATOR
        //may output -21,3, or some other value

        System.out.println(Arrays.asList("w", "o", "l", "f")
                .parallelStream()
//				.stream()  // prints Xwolf in this case
                .reduce("X", String::concat));// may output XwXoXlXf
        //	As part of the parallel process, the identity is applied to
        // multiple elements in the stream, resulting in very unexpected data.
        Arrays.asList(5, 2, 1, 4, 5).parallelStream().forEach(System.out::println);
        System.out.println();
        Arrays.asList(5, 2, 1, 4, 5).parallelStream().forEachOrdered(System.out::println);


    }
}
