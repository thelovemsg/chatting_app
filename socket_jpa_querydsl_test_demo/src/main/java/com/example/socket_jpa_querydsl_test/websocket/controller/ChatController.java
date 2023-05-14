package com.example.socket_jpa_querydsl_test.websocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ChatController {

    @MessageMapping("/send")//react.js에서 publish 하는 주소. prefix인 /app이 같이 붙는다.
    @SendTo ("/topic/messages") //react.js에서 subscribe 하는 주소
    public String handleMessage(String message) {
        return message;
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Exception exception) {
        // Log the error
        log.error(exception.getMessage());

        // Notify the user by sending an error message
        return "An error occurred: " + exception.getMessage();
    }
}
