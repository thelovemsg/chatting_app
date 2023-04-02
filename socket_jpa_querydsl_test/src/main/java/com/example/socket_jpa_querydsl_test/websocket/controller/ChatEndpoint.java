package com.example.socket_jpa_querydsl_test.websocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ChatEndpoint {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatEndpoint(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String handleChatMessage(String message) {
        log.info("message = {}", message);
        return message;
    }

    @MessageMapping("/private.chat/{userId}")
    public void sendPrivateMessage(@DestinationVariable String userId, String message) {
        log.info("message = {}", message);
        messagingTemplate.convertAndSend("/user/" + userId + "/queue/chat", message);
    }
}
