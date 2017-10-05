package com.foogaro.experiments.vertx.http.client;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;

public class Client extends AbstractVerticle {

    public static void main(String[] args) {
        Launcher.main(new String[]{"run", Client.class.getName()});
    }


    @Override
    public void start() throws Exception {
        vertx.createHttpClient().getNow(8080, "localhost", "/ko", response -> {
            System.out.println("Got response " + response.statusCode());
            response.handler(body -> System.out.println("Got data " + body.toString("ISO-8859-1")));
        });
    }
}
