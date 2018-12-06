package stas.collections;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Deque_DEMO {
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        System.out.println("The removed element is: " + deque.remove()); // ERROR?

    }

}
