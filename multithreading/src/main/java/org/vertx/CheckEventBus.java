package org.vertx;

import io.vertx.core.Vertx;

public class CheckEventBus {
    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        System.out.println("Starting Receivers");
        vertx.deployVerticle(new EventBusReceiverVerticle("R1"));
        vertx.deployVerticle(new EventBusReceiverVerticle("R2"));

        Thread.sleep(3000);
        vertx.deployVerticle(new EventBusSenderVerticle());

    }
}
