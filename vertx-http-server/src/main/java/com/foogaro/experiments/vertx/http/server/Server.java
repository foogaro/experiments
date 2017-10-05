package com.foogaro.experiments.vertx.http.server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Launcher;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class Server extends AbstractVerticle {

    public static void main(String[] args) {
        Launcher.main(new String[] { "run", Server.class.getName()});
    }

    @Override
    public void start(Future<Void> future) throws Exception {
        Router router = Router.router(vertx);
        router.get("/").handler(this::ok);
        router.get("/ok").handler(this::ok);
        router.get("/ko").handler(this::ko);

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8080, result -> {
                    if (result.succeeded()) {
                        future.complete();
                    } else {
                        future.fail(result.cause());
                    }
                });
    }

    private void ok(RoutingContext rc) {
        rc.response()
                .putHeader("Content-type", "application/json")
                .setStatusCode(200)
                .end("0K");
    }

    private void ko(RoutingContext rc) {
        rc.response()
                .putHeader("Content-type", "application/json")
                .setStatusCode(500)
                .end("N0K");
    }


}
