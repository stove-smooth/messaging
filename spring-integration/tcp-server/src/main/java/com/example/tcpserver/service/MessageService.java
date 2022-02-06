package com.example.tcpserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

    private final RedisTemplate redisTemplate;

    private static final String DEFAULT_RESPONSE = "Q";

    private static int num = 0;

    public String processMessage(String message) {
        num++;
        log.info("{}. Receive message : {}", num, message);

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        valueOperations.set(String.valueOf(num), message);

        return DEFAULT_RESPONSE;
    }
}