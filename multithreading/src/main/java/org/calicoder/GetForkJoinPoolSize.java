package org.calicoder;

import java.util.concurrent.ForkJoinPool;

public class GetForkJoinPoolSize {
    public static void main(String[] args) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        System.out.println(pool.getPoolSize());
    }
}
