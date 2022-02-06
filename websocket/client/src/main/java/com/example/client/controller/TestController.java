package com.example.client.controller;

import com.example.client.util.WebsocketUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.drafts.Draft_6455;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TestController {

    private final WebsocketUtil websocketUtil;

    @GetMapping
    public void test(
            @RequestParam(value = "value") String value
    ) throws Exception {
        log.info("value : {}", value);

        //웹소켓 메세지 보내기
        websocketUtil.send(value);
    }
}
