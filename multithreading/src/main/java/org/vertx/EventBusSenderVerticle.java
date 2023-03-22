package org.vertx;

import io.vertx.core.AbstractVerticle;

import io.vertx.core.Future;

public class EventBusSenderVerticle extends AbstractVerticle {
    public void start(Future<Void> startFuture) {
        vertx.eventBus().publish("anAddress","message abc");
        vertx.eventBus().send("anAddress","message 123");
    }
}
