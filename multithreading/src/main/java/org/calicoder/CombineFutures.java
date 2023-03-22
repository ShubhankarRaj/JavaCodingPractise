package org.calicoder;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CombineFutures {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Retrieving weight!!");
        CompletableFuture<Double> weightInKg = CompletableFuture.supplyAsync(() -> {
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException e){
                throw new IllegalStateException(e);
            }
            return 76.45;
        });

        System.out.println("Retrieving Height");
        CompletableFuture<Double> heightInCm = CompletableFuture.supplyAsync(()->{
            try{
                TimeUnit.SECONDS.sleep(4);
            }catch(InterruptedException e){
                throw new IllegalStateException(e);
            }
            return 167.45;
        });

        System.out.println("Calculating BMI");
        CompletableFuture<Double> combinedFuture = weightInKg.thenCombine(heightInCm, (weight, height) -> {
            System.out.println(weight+" "+height);
            Double heightInMeter = height/100;
            return weight/(heightInMeter*heightInMeter);
        });

        System.out.println(combinedFuture.get());
    }
}
