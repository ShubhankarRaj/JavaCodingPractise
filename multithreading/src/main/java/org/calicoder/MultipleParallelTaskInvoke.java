package org.calicoder;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class MultipleParallelTaskInvoke {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(5);

        Callable<String> task1 = () -> {
            Thread.sleep(2000);
            return "Result Of Task 1";
        };

        Callable<String> task2 = () -> {
            Thread.sleep(500);
            return "Result Of Task 2";
        };

        Callable<String> task3 = () -> {
            Thread.sleep(20000);
            return "Result of Task 3";
        };

        /*
        Two different approaches... one wud print the fastest precessed output,
        other would print the output once the longest one is completed
         */
        List<Callable<String>> taskList = Arrays.asList(task1, task2, task3);
        String result = service.invokeAny(taskList);
        System.out.println(result);

        List<Future<String>> futures = service.invokeAll(taskList);
        for (Future<String> future:futures){
            System.out.println(future.get());
        }
        service.shutdown();
    }
}
