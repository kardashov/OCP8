package stas.collections;

import java.util.HashMap;
import java.util.Set;

public class HashMap_DEMO {

    public HashMap_DEMO() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        HashMap<String, String> hmVar = new HashMap<>(0x34, 0.15f);
        System.out.println(hmVar.remove("valbe"));


        hmVar.put("key2", "val2");
        hmVar.put("key3", "val3");
        System.out.println(hmVar.size());

        Set<String> m = hmVar.keySet();
        System.out.println(m);

        System.out.println(hmVar);
    }

}
