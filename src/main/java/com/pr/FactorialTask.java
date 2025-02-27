package com.pr;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {

    private final int N;
    private final int FIRST;

    public FactorialTask(int N, int first) {
        this.N = N;
        this.FIRST = first;
    }

    @Override
    public Long compute() {
        if (FIRST >= N) {
            return (long) N;
        } else if (FIRST == N - 1) {
            return (long) FIRST * N;
        }
        FactorialTask rightTask = new FactorialTask(N, FIRST + 2);
        rightTask.fork();

        Long leftResult = (long) FIRST * (FIRST + 1);

        Long rightResult = rightTask.join();

        return leftResult * rightResult;
    }
}
