package org.calicoder;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class SupplyAndApply {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> yourName = CompletableFuture.supplyAsync(() -> {
            try{
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Raj";
        }).thenApply(name -> {
            return "Hiiiiiiii " + name;
        }).thenApply(subject -> {
            return subject + " I Love You So So So So Much!!!!!";
        });
//
//        CompletableFuture<String> greetYourName = yourName.thenApply(name -> {
//            return "Hiiiiiiii " + name;
//        });
//
//        CompletableFuture<String> professLove = greetYourName.thenApply(subject -> {
//            return subject + " I Love You So So So So Much!!!!!";
//        });

        System.out.println(yourName.get());

    }
}
