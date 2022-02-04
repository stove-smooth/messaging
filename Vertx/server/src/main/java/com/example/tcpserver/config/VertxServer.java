package com.example.tcpserver.config;

import io.vertx.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class VertxServer extends AbstractVerticle {

    private static final int port = 8080;

    @Override
    public void start() throws Exception {
        vertx.createHttpServer().webSocketHandler(ws ->
                ws.handler(ws::writeBinaryMessage)
        ).requestHandler(req -> {
            log.info(req.toString());
        }).listen(port);
    }

}
