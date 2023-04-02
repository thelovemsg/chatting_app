package com.example.socket_jpa_querydsl_test.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatEndpoint {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatEndpoint(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String handleChatMessage(String message) {
        System.out.println("handleChatMessage working ... :: message = " + message);
        return message;
    }
}
