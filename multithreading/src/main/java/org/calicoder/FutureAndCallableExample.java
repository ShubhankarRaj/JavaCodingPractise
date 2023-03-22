package org.calicoder;

import java.util.concurrent.*;

public class FutureAndCallableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            System.out.println("Entered callable");
            Thread.sleep(1);
            System.out.println("Finished Sleep in callable");
            return "Hello !! Done with callable";
        };
        System.out.println("Submitting Callable");
        Future<String> future = executorService.submit(callable);

        for(int i = 0; i < 20; i++)
            System.out.println("Even after submitting callable, this line get executed");
        System.out.println("Infact, it get executed 20 times");

        System.out.println("Now retrieving the result of the FUTURE");
        String resultTypeIsString = future.get();
        System.out.println(resultTypeIsString);

        System.out.println("Shutting down the Executor");
        executorService.shutdown();

    }
}
