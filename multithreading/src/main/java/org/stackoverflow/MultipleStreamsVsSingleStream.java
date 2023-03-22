package org.stackoverflow;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MultipleStreamsVsSingleStream {
    private static Integer sleepTask(int number){
        System.out.println(String.format("Sleeping for %d",number));
        try{
            TimeUnit.SECONDS.sleep(number);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
        return number;
    }

    public static void main(String[] args) {
        List<Integer> sleepTimes = Arrays.asList(1,2,3,4,5,10);
        System.out.println("WITH SEPARATE STREAMS FOR FUTURE AND JOIN");

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        // Starting now with separate streams
        long start = System.currentTimeMillis();
        //  The supplyAsync that we are using here is running each thread in parallel. and the proof is that all the Futures
        //  are getting completed within the highest time that is there to sleep
        List<CompletableFuture<Integer>> futures = sleepTimes.stream()
                .map(sleepTime -> CompletableFuture.supplyAsync(() -> sleepTask(sleepTime), executorService)
                .exceptionally(ex -> { ex.printStackTrace(); return -1;}))
                .collect(Collectors.toList());

        executorService.shutdown();
        List<Integer> result = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        long finish = System.currentTimeMillis();
        long timeElapsed = (finish-start)/1000;
        System.out.println(String.format("Completed in %d seconds!", timeElapsed));
        System.out.println(result);

        System.out.println("WITH SAME STREAM FOR FUTURE AND JOIN");
        ExecutorService executorService2 = Executors.newFixedThreadPool(6);
        start = System.currentTimeMillis();
        List<Integer> results = sleepTimes.stream()
                .map(sleepTime -> CompletableFuture.supplyAsync(() -> sleepTask(sleepTime), executorService2)
                        .exceptionally(ex -> { ex.printStackTrace(); return -1; }))
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        executorService2.shutdown();
        finish = System.currentTimeMillis();
        timeElapsed = (finish - start)/1000;
        System.out.println(String.format("done in %d seconds.", timeElapsed));
        System.out.println(results);
    }
}
