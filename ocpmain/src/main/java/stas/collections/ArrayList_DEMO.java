package stas.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


class ArrayList_DEMO {
    public static void main(String[] args) {

        String[] words = {"ace", "boom", "crew", "dog", "eon"};
        String[] words2 = {"boom", "ace", "crew", "dog", "eon"};

        List<? extends String> f = new ArrayList<>();
//	   f.add(123);
        f.clear();
//	   String c =f.get(1);
//	    Long i = f.get(1);

        System.out.println(Arrays.asList(words).getClass().getName());
        List<String> strList = new ArrayList<>();

        strList.add("abc");
        strList.add("ghi");
        strList.add("def");

        strList.sort(null);
        System.out.println(strList);


        List<String> wordList = Arrays.asList(words);

//		wordList.set(3, "Stas");
        System.out.println(wordList.get(3) + "\n");
//		wordList.add("sdfdf");
        System.out.println(Arrays.toString(words));

        Collections.shuffle(wordList);
        for (String word : wordList) {
            System.out.println(word);
        }

    }
}


