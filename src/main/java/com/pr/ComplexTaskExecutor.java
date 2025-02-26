package com.pr;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComplexTaskExecutor {

    private final ExecutorService executorService;
    private final CyclicBarrier barrier;
    private final ComplexTask task;
    private final List<String> results = new CopyOnWriteArrayList<>();

    public ComplexTaskExecutor() {
        this.executorService = Executors.newFixedThreadPool(4);
        this.task = new ComplexTask();
        barrier = new CyclicBarrier(4, this::aggregateResults);
    }

    public void executeTasks() {
        submitTask(task::boilMeat);
        submitTask(task::makeFry);
        submitTask(task::chopCabbage);
        submitTask(task::chopPotatoes);
        submitTask(task::peelPotatoes);

        executorService.shutdown();
    }

    private void submitTask(Callable<String> callable) {
        executorService.submit(() -> {
            try {
                results.add(callable.call());
                awaitBarrier();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void awaitBarrier() {
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void aggregateResults() {
        String finalResult = String.join("\n", results);
        System.out.println(finalResult + "\nВсе этапы завершены! Щи готовы!");
    }
}
