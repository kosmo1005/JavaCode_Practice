package com.pr;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("TV", 1800.0),
                new Order("PC", 1000.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("PC", 800.0),
                new Order("Tablet", 500.0),
                new Order("TV", 1000.0),
                new Order("PC", 3000.0),
                new Order("Smartphone", 900.0),
                new Order("TV", 1500.0),
                new Order("PC", 1600.0)

        );

        Map<String, Double> result = orders.stream()
                .collect(Collectors.groupingBy( // группировка по продукту
                        Order::getProduct,
                        Collectors.summingDouble(Order::getCost) // суммирование цен
                ))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) // сортировка по убыванию
                .limit(3) // выделение первой тройки значений
                .collect(Collectors.toMap( // сбор в итоговую мапу с сохранением порядка вхождения
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        System.out.println(result);


    }
}

