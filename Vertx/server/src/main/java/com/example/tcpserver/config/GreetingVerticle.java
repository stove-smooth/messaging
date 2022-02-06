package com.example.tcpserver.config;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Slf4j
@Component
@Scope(SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class GreetingVerticle extends AbstractVerticle {

    private final Greeter greeter;

    @Override
    public void start(Promise<Void> startPromise) {
        vertx.createHttpServer().requestHandler(request -> {
            String name = request.getParam("name");
            log.info("Got request for name: {}", name);
            if (name == null) {
                request.response().setStatusCode(400).end("Missing name");
            } else {
                // It's fine to call the greeter from the event loop as it's not blocking
                request.response().end(greeter.sayHello(name));
            }
        }).listen(8080, ar -> {
            if (ar.succeeded()) {
                log.info("GreetingVerticle started: @{}", this.hashCode());
                startPromise.complete();
            } else {
                startPromise.fail(ar.cause());
            }
        });
    }
}
