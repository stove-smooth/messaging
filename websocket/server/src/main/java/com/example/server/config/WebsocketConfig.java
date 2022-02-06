package com.example.server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

    private final MessageHandler messageHandler;

    private static final int MESSAGE_BUFFER_SIZE = 8192;

    @Bean
    public ServletServerContainerFactoryBean createServletServerContainerFactoryBean() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(MESSAGE_BUFFER_SIZE);
        container.setMaxBinaryMessageBufferSize(MESSAGE_BUFFER_SIZE);
        return container;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(messageHandler, "/ws")
                .setAllowedOrigins("*");
    }
}
