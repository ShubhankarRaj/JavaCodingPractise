package org.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;


public class MyVerticle extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        super.start(startFuture);
        System.out.println("MyVerticle started!");
    }

    @Override
    public void stop(Future stopFuture) throws Exception {
        super.stop(stopFuture);
        System.out.println("MyVerticle stopped!");
    }
}
