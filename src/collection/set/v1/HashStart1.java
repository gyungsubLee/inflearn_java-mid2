package collection.set.v1;

import java.util.Arrays;

public class HashStart1 {

    public static void main(String[] args) {
        Integer[] inputArray = new Integer[4];
        inputArray[0] = 1;
        inputArray[1] = 3;
        inputArray[2] = 5;
        inputArray[3] = 6;
        System.out.println("Arrays.toString(inputArray) = " + Arrays.toString(inputArray));

        int searchValue = 8;
        // O(n)
        for (Integer inputValue : inputArray) {
            if (inputValue == searchValue) {
                System.out.println(inputValue);
            }
        }
    }
}
