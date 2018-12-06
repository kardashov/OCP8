package stas.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

public class _Gson_DEMO {
    // https://github.com/google/gson/blob/master/UserGuide.md#TOC-Primitives-Examples
    // https://futurestud.io/tutorials/gson-mapping-of-nested-objects
    // http://www.studytrails.com/java/json/java-google-json-serializing-classes-with-generic-type/
    public static void main(String[] args) {

        // Serialization  of primitives
        Gson gson = new Gson();
        gson.toJson(1); // ==> 1
        System.out.println(gson.toJson("abcd")); // ==> "abcd"
        System.out.println(gson.toJson(new Long(10))); // ==> 10

        int[] values = {1};
        System.out.println(gson.toJson(values)); // ==> [1]

        // Deserialization
        int one = gson.fromJson("1", int.class);
        Integer myInt = gson.fromJson("1", Integer.class);
        Long myLong = gson.fromJson("1", Long.class);
        Boolean myFalse = gson.fromJson("false", Boolean.class);
        String str = gson.fromJson("\"abc\"", String.class);
        String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);


        // Serialization of Objects
        BagOfPrimitives obj = new BagOfPrimitives();
        gson = new Gson();
        String json = gson.toJson(obj);
        System.out.println(json);// ==> json is {"value1":1,"value2":"abc"}

        // Deserialization
        BagOfPrimitives obj2 = gson.fromJson(json, BagOfPrimitives.class);
        // ==> obj2 is just like obj

        System.out.println("====================== Array Examples ===============================");
        gson = new Gson();
        int[] ints = {1, 2, 3, 4, 5};
        String[] strings = {"abc", "def", "ghi"};

        // Serialization
        gson.toJson(ints);     // ==> [1,2,3,4,5]
        gson.toJson(strings);  // ==> ["abc", "def", "ghi"]

        // Deserialization
        int[] ints2 = gson.fromJson("[1,2,3,4,5]", int[].class);
        // ==> ints2 will be same as ints


        System.out.println("====================== Collections Examples ===============================");

        gson = new Gson();
        Collection<Integer> ints1 = Arrays.asList(1, 2, 3, 4, 5);

        // Serialization
        json = gson.toJson(ints1); // ==> json is [1,2,3,4,5]

        // Deserialization
        Type collectionType = new TypeToken<Collection<Integer>>() {
        }.getType();

        Collection<Integer> ints21 = gson.fromJson(json, collectionType);
        // ==> ints2 is same as ints
        System.out.println(ints21);

    }

    static class BagOfPrimitives {
        private int value1 = 1;
        private String value2 = "abc";
        private transient int value3 = 3;

        BagOfPrimitives() {
            // no-args constructor
        }
    }
}
