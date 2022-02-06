package com.example.tcpserver.config;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.spi.VerticleFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class VerticleConfig {

    @PostConstruct
    public void init() {
        Vertx vertx = Vertx.vertx();
        VerticleFactory verticleFactory = context.getBean(SpringVerticleFactory.class);
        vertx.registerVerticleFactory(verticleFactory);
        DeploymentOptions options = new DeploymentOptions().setInstances(4);
        vertx.deployVerticle(verticleFactory.prefix() + ":" + GreetingVerticle.class.getName(), options);
    }
}
