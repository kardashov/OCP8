package stas.variables_operators_loops;

import java.util.ArrayList;
import java.util.List;

public class NUMBERS_ALL {
    // take an array of numbers and sum them up

    public static double sum(Number[] nums) {
        double sum = 0.0;
        for (Number num : nums) {
            System.out.println(num.getClass().getName());
            sum += num.doubleValue();
        }
        return sum;
    }

    public static void main(String[] s) {
        // Create a Number array
        Number nums[] = new Number[4];
        // assign derived class objects
        nums[0] = new Byte((byte) 10);
        nums[1] = new Integer(10);
        nums[2] = new Float(10.0f);
        nums[3] = new Double(10.0);

        // pass the Number array to sum and print the result
        System.out.println("The sum of numbers is: " + sum(nums));

        // ========== operator promotion rules!=================
        // if double => double
        // else if float => float
        // else if long => long
        // else if => int
        byte bb = 3;
        short ss = 15;
        // bb = ss; // �� ��������, �.�.
        final int finalInt = 15;
        ss = finalInt;  // � ��� ��������, �.�. finalInteger  -- ��������� ������� ���������
        final int finalBigInt = 1533443434;
        //ss = finalBigInt; // �� �������������, �.�. ��������� ������� �������

        // ss = bb + ss; ������ ����������. ������ ������� int � short
        ss += bb; // ������ ��� �������� ��� �������
        ss += Integer.MAX_VALUE; // � ���� ��� ��������
        ss++; // � ��� ��������


        // part 57 LiveLessons
        // part 62 LiveLessons

        //
        System.out.println(-Integer.MIN_VALUE); // ������� ��� ����� -2147483648
        // !!!

        byte x = -128;
        System.out.println(-x); // 128 ������� ���� �������������� � int,
        // � ����� �������� "-" !

        byte y = 127;
        System.out.println(++y); // -128 ���������/��������� �� ������
        // �������������� � int, ������� �����
        // ������������

        double ccc = 2147483647; // ����� ���� integer-������� ������ ��
        // Integer.MAX_VALUE
        // ccc = 2147483648; // � ��� ��� �� ��������


        // 		int amount = 9L; COMPILE_ERROR
        // 		float fvalue = 102.0; COMPILE_ERROR
        float fvalue = 2234;
        //		int x = 1.0; // DOES NOT COMPILE
        //		short y = 1921222; // DOES NOT COMPILE
        //		int z = 9f; // DOES NOT COMPILE
        //		long t = 192301398193810323; // DOES NOT COMPILE

        float value1 = 102;
        float value2 = (int) 102.0;
//				float value3 = 1f * 0.0; //DOES NOT COMPILE
        float value4 = 1f * (short) 0.0;
//				float value5 = 1f * (boolean)0; //DOES NOT COMPILE

        System.out.print(2147483647 + 1); // -2147483648

        System.out.println(1 + 2 + "Hello"); // 3Hello -- operator evaluation
        // order!
        System.out.println("Hello" + 1 + 2); // Hello12
        boolean e = true;

//		long x = 5;
//		long y = (x=3);
//		System.out.println(x); // Outputs 3
//		System.out.println(y); // Also, outputs 3


//		long x = 10;
//		int y = 5;
//		y *= x;
//		The compound operator will first cast x to a long, apply the multiplication of two long
//		values, and then cast the result to an int.

        long a = 111111111111L;
        float f = a;
        a = (long) f;
        System.out.println(a);// 111111110656, � float �� ��������� ��� ��������
        // ����� �� long
        a = (long) 3455.34234;
        System.out.println(a); //3455 ������� ����� double ����������

        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }


        float fmin = Float.NEGATIVE_INFINITY;
        float fmax = Float.POSITIVE_INFINITY;

        System.out.println("long: " + (long) fmin + ".." + (long) fmax);
        System.out.println("int: " + (int) fmin + ".." + (int) fmax);
        System.out.println("short: " + (short) fmin + ".." + (short) fmax);
        System.out.println("char: " + (int) (char) fmin + ".." + (int) (char) fmax);
        System.out.println("byte: " + (byte) fmin + ".." + (byte) fmax);

        byte b = 3;
        char c = 'A' + 3;
        long m = b + c;
        double d = m - 3F;

        long see = 3434_0093_344L;

        int i = 1;
        b = 1;

        Integer iObj = new Integer(i);
        Byte bObj = new Byte(b);
        System.out.println("while i==b is " + (i == b));
        System.out.println("iObj.equals(bObj) is " + iObj.equals(bObj));

        String value = "1000";
        Long lObj = new Long(value);
        System.out.println("lObj = " + lObj.toString());

        Long sum = new Long(lObj.longValue() + iObj.byteValue() + bObj.shortValue());
        System.out.println("The sum = " + sum.doubleValue());


        System.out.println("===============Operator evaluation order==================");
        int ast = 10;
        int bst = 20;
        ast += (ast = 4);
        bst = bst + (bst = 5);
        System.out.println(ast + ",  " + bst);  // prints 14, 25
        ///////////////////////////////////////////////////////////////////////////////////////
        ast = 10;
        bst = 20;
        ast += (ast = 4) + (ast = 5);// ast =( ast + (ast = 4) + (ast = 5))
        bst = (bst = 5) + bst + (bst = 6);
        System.out.println(ast + ",  " + bst);  // prints 19, 16
        ///////////////////////////////////////////////////////////////////////////////////////
        ast = 10;
        bst = 20;
        ast = bst++ + ast++ + ++bst + --ast;

        System.out.println(ast + ",  " + bst);// prints 62,  22

        System.out.println("===============Autoboxing==================");
        int primitive = Integer.parseInt("123");
        Integer wrapper = Integer.valueOf("123");
//		int bad1 = Integer.parseInt("a"); // throws NumberFormatException
//		Integer bad2 = Integer.valueOf("123.45"); // throws NumberFormatException
//		page 135!!!

        List<Double> weights = new ArrayList<>();
        weights.add(50.5); // [50.5]
        weights.add(new Double(60)); // [50.5, 60.0]
        weights.remove(50.5); // [60.0]
        double first = weights.get(0); // 60.0

        List<Integer> heights = new ArrayList<>();
        heights.add(null);
//		int h = heights.get(0); // NullPointerException, �.�. autoboxing �������� ������� ����� parseInt() �� null-������
        Integer h = heights.get(0); // �� ��� �������� ��� �������, �.�. autoboxing �� �����������


//		It actually outputs 1. After adding the two values, the List contains [1, 2]. We then request
//		the element with index 1 be removed. That�s right: index 1. Because there�s already a remove()
//		method that takes an int parameter, Java calls that method rather than autoboxing. If you
//		want to remove the 2, you can write numbers.remove(new Integer(2)) to force wrapper class use.
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.remove(1); //�������� �������� � �������� 1, �.�. �������� �� �����������!  numbers.remove(new Integer(2)) -- ������� ������� �� ��������
        System.out.println(numbers); //prints 1


    }
}
