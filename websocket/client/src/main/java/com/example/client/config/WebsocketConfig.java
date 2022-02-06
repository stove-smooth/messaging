package com.example.client.config;

import com.example.client.util.WebsocketUtil;
import org.java_websocket.drafts.Draft_6455;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.net.URI;

@Configuration
public class WebsocketConfig {

    @Bean
    public WebsocketUtil websocketClient() throws Exception{
        URI uri = new URI("ws://localhost:8080/ws");
        WebsocketUtil websocketUtil = new WebsocketUtil(uri, new Draft_6455());

        //웹소켓 커넥팅
        websocketUtil.connectBlocking();

        return websocketUtil;
    }
}
