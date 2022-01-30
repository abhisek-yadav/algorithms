package com.test;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class MultiThreadedScenarioTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("In main() method before: " + Thread.currentThread().getName());
        MultiThreadedScenarioTest t = new MultiThreadedScenarioTest();
        Executor executor = new ForkJoinPool(16);
        IntStream.range(1, 20).forEach(i -> {
            CompletableFuture.runAsync(t::m1, executor);
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread.sleep(5000l);
        System.out.println("In main() method after: " + Thread.currentThread().getName());
    }

    public void m1() {
        try {
            Thread.sleep(500l);
            System.out.println("in m1(): " + Thread.currentThread().getName());
//            System.out.println(Thread.currentThread().getThreadGroup().getName());

            IntStream.range(1, 20).forEach(i -> {
                CompletableFuture.runAsync(this::m2);
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread.sleep(5000l);

//            CompletableFuture.runAsync(this::m2).get();

        } catch (
                InterruptedException ex) {
            //do nothing
        }

    }

    public void m2() {
        try {
            Thread.sleep(100l);
            System.out.println("in m2(): " + Thread.currentThread().getName());
//            System.out.println(Thread.currentThread().getThreadGroup().getName());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
