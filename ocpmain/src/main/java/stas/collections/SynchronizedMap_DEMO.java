package stas.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SynchronizedMap_DEMO {

    public SynchronizedMap_DEMO() {
    }

    public static void main(String[] args) {

        Map<String, String> f = new ConcurrentHashMap<>(new HashMap<>());

        System.out.println(f);
    }

}





