package com.pr;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Очередь в ветеринарную клинику!");
        BlockingQueue queue = new BlockingQueue(5);
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        List<String> doctorNames = List.of("Врач Петров", "Врач Nванов", "Врач Сидоров");
        ExecutorService pandemic = Executors.newFixedThreadPool(3);
        ExecutorService doctors = Executors.newFixedThreadPool(3, new NamedThreadFactory(doctorNames));

        while (true) {
            pandemic.submit(() -> {
                while (true) {
                    queue.enqueue(producer.produce());
                }
            });
            doctors.submit(() -> {
                while (true) {
                    consumer.consume(queue.dequeue());
                }
            });
        }
    }

    static class NamedThreadFactory implements ThreadFactory {
        private final List<String> names;
        private int counter = 0;

        public NamedThreadFactory(List<String> names) {
            this.names = names;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(names.get(counter));
            counter++;
            return thread;
        }
    }
}

