package com.example.tcpserver.config;

import org.springframework.stereotype.Component;

@Component
public class Greeter {

    public String sayHello(String name) {
        return "Hello " + name;
    }
}
