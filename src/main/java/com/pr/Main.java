package com.pr;

import java.util.Collection;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        List<Integer> someList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,5,2,8,8,8,8,8,5,3,25,2,5,2,54,6,35,4,12);
        System.out.println(countOfElement(someList));


    }

    public static <T> Map<T, Long> countOfElement(Collection<T> collection) {
        return collection.stream()
                .distinct()
                .collect(Collectors.toMap(
                        Function.identity(),
                        v -> collection.stream()
                                .filter(el -> el.equals(v))
                                .count()));

    }
}

