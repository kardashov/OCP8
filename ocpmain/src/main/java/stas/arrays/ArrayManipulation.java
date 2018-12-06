package stas.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayManipulation {

    public static void main(String[] args) {

        int[] numbers1 = new int[3];
        // When using this form to instantiate an array, set all the elements to
        // the default value for that type.

        int[] numbers2 = new int[]{42, 55, 939};
        int[] numbers22 = {42, 11, 99}; // shortcut
//		numbers22 = { 42, 55, 99, }; // DOES NOT COMPILE  Array constants can only be used in initializers
        numbers22 = new int[]{42, 55, 99,};//but this compiles fine
        int[] numbers345 = {42,
                55,    // shortcut � ������� ����� ���������� ��������.
                34,}; // ���� ����� ��� �������� �������������� ����

        System.out.println("==================����������� ����������� �������===================================");
//		System.arraycopy(src, srcPos, dest, destPos, length); -- �������� ���������� ������ ������� � ������
        System.out.println("numbers2 before copy" + Arrays.toString(numbers2));
        System.out.println("numbers22 before copy" + Arrays.toString(numbers22));
        System.arraycopy(numbers2, 1, numbers22, 0, 1);
        System.out.println("numbers22 after copy" + Arrays.toString(numbers22));

        int[] numAnimals;
        int[] numAnimals2;
        int numAnimals3[];
        int numAnimals4[];

        String[] bugs = {"cricket", "beetle", "ladybug"};
        String[] alias = bugs;
        System.out.println(bugs.equals(alias)); // true
        System.out.println(bugs.toString()); // [Ljava.lang.String;@160bc7c0
        // The equals() method on arrays does not look at the elements of the
        // array!!!

        // java.util.Arrays.toString(bugs) would print [cricket, beetle,
        // ladybug].

        String[] mammals = {"monkey", "chimp", "donkey"};
        System.out.println(mammals.length); // 3
        System.out.println(mammals[0]); // monkey
        System.out.println(mammals[1]); // chimp
        System.out.println(mammals[2]); // donkey

        String[] birds = new String[6];
        System.out.println(birds.length); // 6
        // The answer is 6. Even though all 6 elements of the array are null,
        // there are still 6 of
        // them. length does not consider what is in the array; it only
        // considers how many slots have
        // been allocated.

        int[] numbers = {6, 9, 1};
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length; i++)
            System.out.print(numbers[i] + " ");

        System.out.println();

        String[] strings = {"10", "9", "100"};
        Arrays.parallelSort(strings);
        Arrays.sort(strings);
        for (String string : strings)  //enhanced FOR LOOP doesn't accept Map
            System.out.print(string + " "); // 10 100 9 -- ���������� ����� �
        // ���������� �������

        int[] numbers12 = {2, 4, 6, 8};
        System.out.println(Arrays.binarySearch(numbers12, 2)); // 0
        System.out.println(Arrays.binarySearch(numbers12, 4)); // 1
        System.out.println(Arrays.binarySearch(numbers12, 1)); // -1
        System.out.println(Arrays.binarySearch(numbers12, 3)); // -2
        System.out.println(Arrays.binarySearch(numbers12, 9)); // -5
        // Take note of the fact that line 3 is a sorted array. If it weren�t,
        // we couldn�t apply either
        // of the other rules. Line 4 searches for the index of 2. The answer is
        // index 0. Line 5 searches
        // for the index of 4, which is 1.
        // Line 5 searches for the index of 1. Although 1 isn�t in the list, the
        // search can determine
        // that it should be inserted at element 0 to preserve the sorted order.
        // Since 0 already means
        // something for array indexes, Java needs to subtract 1 to give us the
        // answer of �1. Line 7
        // is similar. Although 3 isn�t in the list, it would need to be
        // inserted at element 1 to preserve
        // the sorted order. We negate and subtract 1 for consistency, getting
        // �1 �1, also known as
        // �2. Finally, line 8 wants to tell us that 9 should be inserted at
        // index 4. We again negate and
        // subtract 1, getting �4 �1, also known as �5.

        int[][] vars1; // 2D array
        int vars2[][]; // 2D array
        int[] vars3[]; // 2D array
        int[] vars4[], space[][]; // a 2D AND a 3D array

        String[][] rectangle = new String[3][2]; // ������������� ������
        rectangle[0][1] = "set";

        int[][] differentSize = {{1, 4}, {3}, {9, 8, 7}}; // ������
        // ������������
        // �����

        // Another way to create an asymmetric array is to initialize just an
        // array�s first dimension,
        // and define the size of each array component in a separate statement:
        int[][] args1 = new int[4][];
        args1[0] = new int[5];
        args1[1] = new int[3];

        int[][] twoD = new int[3][2];
        for (int i = 0; i < twoD.length; i++) {
            for (int j = 0; j < twoD[i].length; j++)
                System.out.print(twoD[i][j] + " "); // print element
            System.out.println(); // time for a new row
        }

        // using enhanced for loop with 2D array
        for (int[] inner : twoD) {
            for (int num : inner)
                System.out.print(num + " ");
            System.out.println();
        }
        System.out.println("=========================Arrays.deepToString(twoD)=============================");
        System.out.println(Arrays.deepToString(twoD));//prints [[0, 0], [0, 0], [0, 0]]
        System.out.println(Arrays.deepToString(differentSize));//[[1, 4], [3], [9, 8, 7]]
        System.out.println(Arrays.toString(differentSize));//[[I@1b6d3586, [I@4554617c, [I@74a14482]


        System.out.println("=========================Arrays.fill(array[], val)=============================");

        int[] filledArray = new int[15];
        Arrays.fill(filledArray, 15);
        System.out.println(Arrays.toString(filledArray));//[15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15]

        System.out.println("=============================Arrays.setAll()=================================");
        Arrays.setAll(filledArray, t -> t * 2);
        System.out.println(Arrays.toString(filledArray));//[0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28]

        System.out.println("=============================Arrays.sort() and parallelSort()=================================");
        Arrays.parallelSort(filledArray);
        System.out.println(Arrays.toString(filledArray));
        Arrays.sort(filledArray);


        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList(10);
        ArrayList list3 = new ArrayList(list2);

        ArrayList<String> list4 = new ArrayList<String>();
        ArrayList<String> list5 = new ArrayList<>();

        List<String> list6 = new ArrayList<>();
//		ArrayList<String> list7 = new List<>(); // DOES NOT COMPILE

        ArrayList list = new ArrayList();
        list.add("hawk"); // [hawk]
        list.add(Boolean.TRUE); // [hawk, true]
        list.add(true); // [hawk, true, true]
        System.out.println(list); // [hawk, true, true]

        ArrayList<String> safer = new ArrayList<>();
        safer.add("sparrow");
//		safer.add(Boolean.TRUE); // DOES NOT COMPILE

        List<String> birds1 = new ArrayList<>();
        birds1.add("hawk"); // [hawk]
        birds1.add(1, "robin"); // [hawk, robin]
        birds1.add(0, "blue jay"); // [blue jay, hawk, robin]
//		 birds1.add(8, "blue jay"); // ���������� index out of bound exception
        birds1.add(1, "cardinal"); // [blue jay, cardinal, hawk, robin]
        System.out.println(birds1); // [blue jay, cardinal, hawk, robin]


//		 boolean remove(Object object)    boolean return value tells us whether a match was removed
//		 E remove(int index) 	 
        List<String> birds2 = new ArrayList<>();
        birds2.add("hawk"); // [hawk]
        birds2.add("hawk"); // [hawk, hawk]
        System.out.println(birds2.remove("cardinal")); // prints false
        System.out.println(birds2.remove("hawk")); // prints true
        System.out.println(birds2.remove(0)); // prints hawk
        System.out.println(birds2); // []
//		 System.out.println(birds2.remove(0)); // ���������� index out of bound exception 
        System.out.println(birds2.remove("hawk"));  // prints false

        System.out.println("=================sublist()=================");
        //subList(fromIndex, toIndex)    --  toIndex is EXCLUSIVE
        List s1 = new ArrayList();
        s1.add("a");
        s1.add("b");
        s1.add("c");
        List s2 = s1.subList(1, 2);                 //returns list with ONE element
        s2 = s1.subList(1, 1);                     //returns EMPTY LIST
        s1.addAll(s2);


        System.out.println("=================set()=================");
//		 E set(int index, E newElement)
        List<String> birds3 = new ArrayList<>();
        birds3.add("hawk"); // [hawk]
        System.out.println(birds3.size()); // 1
        birds3.set(0, "robin"); // [robin]
        System.out.println(birds3.size()); // 1
//		 birds3.set(1, "robin"); // IndexOutOfBoundsException

        System.out.println("==============isEmpty() and size()============");
//		 The isEmpty() and size() methods look at how many of the slots are in use. The method
//		 signatures are as follows:
//		 boolean isEmpty()
//		 int size()
        List<String> birds4 = new ArrayList<String>();
        System.out.println(birds4.isEmpty()); // true
        System.out.println(birds4.size()); // 0
        birds4.add("hawk"); // [hawk]
        birds4.add("hawk"); // [hawk, hawk]
        System.out.println(birds4.isEmpty()); // false
        System.out.println(birds4.size()); // 2

        System.out.println("==============clear()============");

        birds4.clear(); // []
        System.out.println(birds4.isEmpty()); // true
        System.out.println(birds4.size()); // 0

        System.out.println("==============contains()============");
//		 The contains() method checks whether a certain value is in the ArrayList. The method
//		 signature is as follows: 	 boolean contains(Object object)
//		 This method calls equals() on each element of the ArrayList to see whether there are
//		 any matches.

        List<String> birds5 = new ArrayList<>();
        birds5.add("hawk"); // [hawk]
        System.out.println(birds5.contains("hawk")); // true
        System.out.println(birds5.contains("robin")); // false

        System.out.println("==============equals()============");
//		 ArrayList has a custom implementation of equals() so you can compare two lists
//		 to see if they contain the same elements in the same order.
        List<String> one = new ArrayList<>();
        List<String> two = new ArrayList<>();
        System.out.println(one.equals(two)); // true
        one.add("a"); // [a]
        System.out.println(one.equals(two)); // false
        two.add("a"); // [a]
        System.out.println(one.equals(two)); // true
        one.add("b"); // [a,b]
        two.add(0, "b"); // [b,a]
        System.out.println(one.equals(two)); // false


        System.out.println("===============Autoboxing==================");
        int primitive = Integer.parseInt("123");
        Integer wrapper = Integer.valueOf("123");
//			int bad1 = Integer.parseInt("a"); // throws NumberFormatException
//			Integer bad2 = Integer.valueOf("123.45"); // throws NumberFormatException
//			page 135!!!

        List<Double> weights = new ArrayList<>();
        weights.add(50.5); // [50.5]
        weights.add(new Double(60)); // [50.5, 60.0]
        weights.remove(50.5); // [60.0]
        double first = weights.get(0); // 60.0

        List<Integer> heights = new ArrayList<>();
        heights.add(null);
//			int h = heights.get(0); // NullPointerException, �.�. autoboxing �������� ������� ����� parseInt() �� null-������
        Integer h = heights.get(0); // �� ��� �������� ��� �������, �.�. autoboxing �� �����������


//			It actually outputs 1. After adding the two values, the List contains [1, 2]. We then request
//			the element with index 1 be removed. That�s right: index 1. Because there�s already a remove()
//			method that takes an int parameter, Java calls that method rather than autoboxing. If you
//			want to remove the 2, you can write numbers.remove(new Integer(2)) to force wrapper class use.
        List<Integer> numbers222 = new ArrayList<>();
        numbers222.add(1);
        numbers222.add(2);
        numbers222.remove(1); //�������� �������� � �������� 1, �.�. �������� �� �����������!
        //	numbers.remove(new Integer(2)) -- ������� ������� �� ��������
        System.out.println(numbers222); //prints 1


        System.out.println(((Object) true).toString());

        System.out.println("===========================Converting Between array and List============================");
        List<String> list34 = new ArrayList<>();
        list34.add("hawk");
        list34.add("robin");

        Object[] objectArray = list34.toArray();

        System.out.println(objectArray.length); // 2
        String[] stringArray = list34.toArray(new String[0]);
        System.out.println(stringArray.length); // 2

//			Converting from an array to a List is more interesting. The original array and created
//			array backed List are linked. When a change is made to one, it is available in the other. It
//			is a fixed-size list and is also known a backed List because the array changes with it. Pay
//			careful attention to the values here:
        String[] array = {"hawk", "robin"}; // [hawk, robin]
        List<String> list15 = Arrays.asList(array); // returns fixed size list
        System.out.println(list15.size()); // 2
        list15.set(1, "test"); // [hawk, test]
        array[0] = "new"; // [new, test]
        for (String b : array) System.out.print(b + " "); // new test
//			list15.remove(1); // throws UnsupportedOperation Exception


        System.out.println("===========================ArrayList Sorting============================");
//			Sorting an ArrayList is very similar to sorting an array. You just use a different helper class:
        List<Integer> numbers35 = new ArrayList<>();
        numbers35.add(99);
        numbers35.add(5);
        numbers35.add(81);
        Collections.sort(numbers35);
        System.out.println(numbers35);// [5, 81, 99]


    }

}
