package com.example.tcpcleint.config;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class VertxClient extends AbstractVerticle {

    private static final int port = 9000;

    @PostConstruct
    public void startClient() throws Exception {
        start();
    }

    @Override
    public void start() throws Exception {
        HttpClient client = vertx.createHttpClient();

        client.webSocket(port, "localhost", "/some-uri").onSuccess(webSocket -> {
            webSocket.handler(data -> {
                System.out.println("Received data " + data.toString("ISO-8859-1"));
                client.close();
            });
            webSocket.writeBinaryMessage(Buffer.buffer("Hello world"));
        }).onFailure(Throwable::printStackTrace);
    }
}
