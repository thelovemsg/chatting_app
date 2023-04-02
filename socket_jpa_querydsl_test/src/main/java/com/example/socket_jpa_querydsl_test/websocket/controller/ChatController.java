package com.example.socket_jpa_querydsl_test.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/send")
    @SendTo ("/topic/messages")
    public String handleMessage(String message) {
        return message;
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Exception exception) {
        // Log the error
        System.err.println("Application-level error: " + exception.getMessage());

        // Notify the user by sending an error message
        return "An error occurred: " + exception.getMessage();
    }
}
