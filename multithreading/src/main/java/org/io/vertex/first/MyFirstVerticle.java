package org.io.vertex.first;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MyFirstVerticle extends AbstractVerticle {
    @Override
    public void start(Future<Void> future_vertex){
        vertx
                .createHttpServer()
                .requestHandler(r -> {
                    r.response().end("<h1>Hello from my first " +
                            "Vert.x 3 application</h1>");
                })
                .listen(8080, result -> {
                    if (result.succeeded()){
                        future_vertex.complete();
                    }else{
                        future_vertex.fail(result.cause());
                    }
                });
    }
}
