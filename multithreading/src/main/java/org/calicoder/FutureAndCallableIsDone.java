package org.calicoder;

import java.util.concurrent.*;

public class FutureAndCallableIsDone {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = executorService.submit(() -> {
            Thread.sleep(2000);
            return "Callable is completed";
        });

        while(!future.isDone()){
            System.out.println("The Parallel Task is still going on");
            Thread.sleep(50);
        }
        // If the future crosses an elapsed time, we can also cancel the future
        // Also, we can add TIMEOUTS when doing future.get()
        System.out.println("Task is completed now!!");
        String result = future.get(3, TimeUnit.SECONDS);
        System.out.println(result);

        executorService.shutdown();
    }

}
