package com.pr;

import java.lang.reflect.Array;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");

        String[] strArr = {null, "b", "c", "d", "e", "f", null};
        Integer[] intArr = {1, 2, 3, 4, null, 6, 7, null, 9, 10};

        String[] filteredStrings = filter(strArr, s -> s + "lol");
        Integer[] filteredIntegers = filter(intArr, n -> n + 1);

        System.out.println(java.util.Arrays.toString(filteredStrings));
        System.out.println(java.util.Arrays.toString(filteredIntegers));

    }

    public static <T> T[] filter(T[] array, Filter<T> filter) {
        @SuppressWarnings("unchecked")
        T[] resultArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);
        int index = 0;
        for (T element : array) {
            if (element != null) {
                resultArray[index++] = filter.apply(element);
            }
        }
        return resultArray;
    }
}

