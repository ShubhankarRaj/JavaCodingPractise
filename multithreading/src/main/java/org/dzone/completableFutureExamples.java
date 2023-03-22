package org.dzone;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class completableFutureExamples {

    static void completedFutureExample(){
        CompletableFuture<String> cf = CompletableFuture.completedFuture("This Future is Complete");
        assertTrue(cf.isDone());
        assertEquals(cf.getNow(null), "This Future is Complete");
    }

    static void runAsyncExample() {

        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            assertTrue(Thread.currentThread().isDaemon());
            try {
                System.out.println("Sleeping for 2 seconds");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        assertFalse(cf.isDone());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(cf.isDone());

    }

    static void acceptBothExmple(){
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).thenAcceptBoth(CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),(s1,s2)->{
            System.out.println(s1);
            System.out.println(s2);
        });
    }

    public static void main(String[] args) {
        completedFutureExample();
        runAsyncExample();
        acceptBothExmple();
    }
}
