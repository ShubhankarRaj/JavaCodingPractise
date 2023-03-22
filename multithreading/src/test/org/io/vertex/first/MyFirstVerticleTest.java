package org.io.vertex.first;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(VertxUnitRunner.class)
public class MyFirstVerticleTest {
    private Vertx vertx;

    @Before
    public void setup(TestContext testContext) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(MyFirstVerticle.class.getName(), testContext.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext testContext) {
        vertx.close(testContext.asyncAssertSuccess());
    }

    @Test
    public void testMyApplication(TestContext testContext) {
        final Async async = testContext.async();

        vertx.createHttpClient().getNow(8080, "localhost", "/",
                response -> {
                    response.handler((body -> {
                        testContext.assertTrue(body.toString().contains("Hello"));
                        async.complete();
                    }));

                }
        );
    }
}

