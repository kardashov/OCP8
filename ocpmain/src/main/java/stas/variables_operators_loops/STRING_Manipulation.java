package stas.variables_operators_loops;

public class STRING_Manipulation {
    public static void main(String[] args) {
        int numFish = 4;
        Integer numFish2 = 2;
        String fishType = "tuna";
        String anotherFish = null;
//		anotherFish = numFish + 1;  // ��� ��������������� �������������� � String �� int ������ ����������
//		anotherFish = 'd';			//���� �� char
//		anotherFish = numFish2; 	//��� ��������������� �������������� � String �� Integer � ������ �������

        System.out.println(anotherFish + " " + fishType); // null tuna
        //������ ��� null-������ ���������� "null"
//		public void println(Object x) {    
//        String s = String.valueOf(x); -->  {return (obj == null) ? "null" : obj.toString();}
//            print(s); }
//		}
        System.out.println(numFish + " " + 1); // 4 1

        System.out.println("gamer".replace('g', 'g') == "gamer"); //true   !!!!!!!!!!!!!!!!!!!!!!!!!
        System.out.println("gamer".replace('g', 'd') == "gamer"); // �� ����� false   !!!!!!!!!!!!!!!!!!!!!!!!!
        System.out.println("gamer".replace("g", "g") == "gamer"); // � ����� ���� false
        //���� � String.replace(char, char) ������ � ������ ��������� ���������, �� ������������
        //


        System.out.println(1 + 2); // 3
        System.out.println("a" + "b"); // ab
        System.out.println("a" + "b" + 3); // ab3
        System.out.println(1 + 2 + "c"); // 3c

        String string = "animals";
        System.out.println(string.length()); // 7

        System.out.println(string.charAt(0)); // a
        System.out.println(string.charAt(6)); // s
//		System.out.println(string.charAt(7)); // throws runtime exception 

        System.out.println(string.indexOf('a')); // 0
        System.out.println(string.indexOf("al")); // 4
        System.out.println(string.indexOf('a', 4)); // 4
        System.out.println(string.indexOf("al", 5)); // -1

        System.out.println(string.substring(3)); // mals
        System.out.println(string.substring(string.indexOf('m'))); // mals
        System.out.println(string.substring(3, 4)); // m
        System.out.println(string.substring(3, 7)); // mals

        System.out.println(string.substring(3, 3)); // empty string
//		System.out.println(string.substring(3, 2)); // throws exception
//		System.out.println(string.substring(3, 8)); // throws exception
//		System.out.println(string.substring(-4, -2)); // throws exception String index out of range: -2

        System.out.println(string.toUpperCase()); // ANIMALS
        System.out.println("Abc123".toLowerCase()); // abc123

        System.out.println("abc".equals("ABC")); // false
        System.out.println("ABC".equals("ABC")); // true
        System.out.println("abc".equalsIgnoreCase("ABC")); // true

        System.out.println("abc".startsWith("a")); // true
        System.out.println("abc".startsWith("A")); // false
        System.out.println("abc".endsWith("c")); // true
        System.out.println("abc".endsWith("a")); // false

        System.out.println("abc".contains("b")); // true
        System.out.println("abc".contains("B")); // false


//		String replace(char oldChar, char newChar)
//		String replace(CharSequence oldChar, CharSequence newChar) 

        System.out.println("abcabc".replace('a', 'A')); // AbcAbc
        System.out.println("abcabc".replace("a", "A")); // AbcAbc

        System.out.println("abc".trim()); // abc
        System.out.println("\t a b c\n".trim()); // a b c

        String result = "AniMaL ".trim().toLowerCase().replace('a', 'A');
        System.out.println(result);


        System.out.println("==============================StringBuilder usage==================================");

//StringBuilder constructors		
        StringBuilder sb = new StringBuilder();
//			  sb = new StringBuilder(int capacity);
        sb = new StringBuilder("some String");
//			  sb = new StringBuilder(CharSequence seq);
        System.out.println("Length of sb is " + sb.length());


        sb = new StringBuilder("start");
        System.out.println("start".contentEquals(sb)); //prints true


        sb.append("+middle"); // sb = "start+middle"
        StringBuilder same = sb.append("+end"); // "start+middle+end"
//		same.append(char[] str, offset, len);


        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder("animal");
        StringBuilder sb3 = new StringBuilder(10);


        sb = new StringBuilder("animals");
        String sub = sb.substring(sb.indexOf("a"), sb.indexOf("al"));

        ////////////////////////////////////////////////////////////////////////////////
        //////		substring() returns a String rather than a StringBuilder	////////
        ////////////////////////////////////////////////////////////////////////////////
        int len = sb.length();
        char ch = sb.charAt(6);
        System.out.println(sub + " " + len + " " + ch); // prints   "anim 7 s"

        //StringBuilder append(String str)
        sb = new StringBuilder().append(1).append('c');
        sb.append("-").append(true);
        System.out.println(sb); // 1c-true


        sb = new StringBuilder("animals");
        sb.insert(7, "-"); // sb = animals-
        sb.insert(0, "-"); // sb = -animals-
        sb.insert(4, "-"); // sb = -ani-mals
        System.out.println(sb);


        sb = new StringBuilder("animals");
        sb.insert(7, "-"); // sb = animals-
        sb.insert(0, "-"); // sb = -animals-
        sb.insert(4, "-"); // sb = -ani-mals
        System.out.println(sb);


        sb = new StringBuilder("abcdef");
        sb.delete(1, 3); // sb = adef
//		sb.deleteCharAt(5); // throws an exception
        System.out.println(sb);


        sb = new StringBuilder("ABC");
        sb.reverse();
        System.out.println(sb);

        String x = "Hello World";
        String y = "Hello World";
        System.out.println(x == y); // true

        String xx = "Hello World";
        String zz = " Hello World".trim();
        System.out.println(xx == zz); // false

        String s = "Hello";
        String t = new String(s);
        if ("Hello".equals(s)) System.out.println("one");   //prints
        if (t == s) System.out.println("two");
        if (t.equals(s)) System.out.println("three"); //prints
        if ("Hello" == s) System.out.println("four");  //prints
        if ("Hello" == t) System.out.println("five");


    }
}
