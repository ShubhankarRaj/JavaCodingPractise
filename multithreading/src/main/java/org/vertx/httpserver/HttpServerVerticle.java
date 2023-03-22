package org.vertx.httpserver;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;


public class HttpServerVerticle extends AbstractVerticle {

    private HttpServer httpServer = null;

    @Override
    public void start() throws Exception {
        httpServer = vertx.createHttpServer();
        httpServer.requestHandler(new Handler<HttpServerRequest>() {
            @Override
            public void handle(HttpServerRequest request){
                System.out.println("Incoming request: ");
                Buffer fullRequestBody = Buffer.buffer();
                if (request.method() == HttpMethod.POST){
                    request.handler(new Handler<Buffer>() {
                        @Override
                        public void handle(Buffer buffer){
                            fullRequestBody.appendBuffer(buffer);
                        }
                    });
                }
                request.uri();
                request.path();
                request.getParam("P1");
                /*request.endHandler(new Handler<Buffer>() {
                    @Override
                    public void handle(Buffer buffer){
                        // here you can access the
                        // fullRequestBody Buffer instance.
                    }
                });*/
                HttpServerResponse response = request.response();
                response.setStatusCode(200);
                response.headers()
                        .add("Content-Length", String.valueOf(57))
                        .add("Content-Type", "text/html")
                ;
                response.write("Vert.x is alive!");
                response.end();
            }
        });
        httpServer.listen(9999);
    }
}
