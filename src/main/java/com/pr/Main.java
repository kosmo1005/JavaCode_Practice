package com.pr;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) throws Exception {
        int n = 4; // Вычисление факториала для числа 10

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialTask factorialTask = new FactorialTask(n,1);

        long result = forkJoinPool.invoke(factorialTask);

        System.out.println("Факториал " + n + "! = " + result);


    }
}

