package org.vertx;

import io.vertx.core.Vertx;

import java.util.Set;

public class VertxApp {
    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MyVerticle());
        Thread.currentThread().sleep(3000);

        Set<String> deploymentIDs = vertx.deploymentIDs();
        System.out.println("==============  (sleeped 3000ms wait for Context allocated), list of deploymentIDs: number Deployments =" + deploymentIDs.size());
        for(String depId: deploymentIDs){
            //
            System.out.println(depId);
        }

        vertx.deploymentIDs().forEach(vertx::undeploy);

    }
}
