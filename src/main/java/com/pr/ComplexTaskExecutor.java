package com.pr;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComplexTaskExecutor {

    private final ExecutorService executorService;
    private final CyclicBarrier barrier;
    private final ComplexTask task;

    public ComplexTaskExecutor() {
        this.executorService = Executors.newFixedThreadPool(4);
        this.task = new ComplexTask();
        this.barrier = new CyclicBarrier(4, () -> System.out.println("Все этапы завершены! Щи готовы!"));
    }

    public void executeTasks() {
        executorService.submit(() -> {
            task.boilMeat();
            awaitBarrier();
        });

        executorService.submit(() -> {
            task.makeFry();
            awaitBarrier();
        });

        executorService.submit(() -> {
            task.chopCabbage();
            awaitBarrier();
        });

        executorService.submit(() -> {
            task.chopPotatoes();
            awaitBarrier();
        });

        executorService.shutdown();
    }

    private void awaitBarrier() {
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
        }
    }
}
