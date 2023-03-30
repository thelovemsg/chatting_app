package com.example.socket_jpa_querydsl_test.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/send")
    @SendTo ("/topic/messages")
    public String handleMessage(String message) {
        return message;
    }
}
