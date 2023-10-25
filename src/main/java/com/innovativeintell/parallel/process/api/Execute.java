package com.innovativeintell.parallel.process.api;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Execute {

    private static int numberOfThreads = 10;

    private static BlockingQueue queue = new ArrayBlockingQueue(10000);

    public static void main(String args[]) throws InterruptedException {

        for (int i=0; i< 10000; i++){
            queue.put(i);
        }
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        for (int i=0; i< numberOfThreads; i++) {
            executorService.execute(() -> {

            });
        }

    }
}
