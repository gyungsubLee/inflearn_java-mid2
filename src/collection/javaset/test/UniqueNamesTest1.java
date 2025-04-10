package collection.javaset.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

public class UniqueNamesTest1 {
    public static void main(String[] args) {
        Integer[] inputArr = {30, 20, 20, 10, 10};

        // tree.addAll(Arrays.asList(inputArr));
        TreeSet<Integer> tree = new TreeSet<>(Arrays.asList(inputArr));

        tree.descendingSet().forEach(System.out::println);
    }
}
