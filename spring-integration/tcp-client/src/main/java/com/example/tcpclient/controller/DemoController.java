package com.example.tcpclient.controller;

import com.example.tcpclient.config.TcpClientGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DemoController {

    private final TcpClientGateway tcpClientGateway;

    @GetMapping
    public void test(
            @RequestParam(value = "value") String value
    ) {
        log.info(value);
        tcpClientGateway.send(value);
    }
}
