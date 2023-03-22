package org.vertx.httpserver;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;

public class DeployHttpVerticle {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new HttpServerVerticle(), stringAsyncResult -> {
            System.out.println("Verticle Deployment Complete");
        });

        vertx.deployVerticle(new AnotherHTTPServerExample(), stringAsyncResult -> {
            System.out.println("Another Verticle deployed");
        });
    }
}
