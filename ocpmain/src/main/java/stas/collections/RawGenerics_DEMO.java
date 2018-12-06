package stas.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class RawGenerics_DEMO {
    public static void main(String[] args) {
        List list = new LinkedList();
        list.add("First");
        list.add(18);
        List<String> strList = list;
//		strList.add(10);// #1: generates compiler error
        for (Iterator<String> itemItr = strList.iterator(); itemItr.hasNext(); )
            System.out.println("Item : " + itemItr.next());

        List<String> strList2 = new LinkedList<>();
        strList2.add("First");
        strList2.add("Second");
        List list2 = strList2;

        list2.add(10);// #2: compiles fine, results in runtime exception
// 		strList2 = list2; // this also compiles fine
        for (Iterator<String> itemItr = list2.iterator(); itemItr.hasNext(); )
            System.out.println("Item : " + itemItr.next());
    }
}
