package com.example.tcpserver.config;

import com.example.tcpserver.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@MessageEndpoint
@RequiredArgsConstructor
public class TcpServerEndpoint {

    private final MessageService messageService;

    @ServiceActivator(inputChannel = "inboundChannel", async = "true")
    public String process(String message) {
        return messageService.processMessage(message);
    }
}