package com.example.socket_jpa_querydsl_test.websocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@Slf4j
public class ChatEndpoint {

    private final SimpMessagingTemplate messagingTemplate;
    private final Map<String, Set<Principal>> roomUserMapping = new ConcurrentHashMap<>();

    public ChatEndpoint(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat/{roomId}/join")
    public void join(@DestinationVariable String roomId, Principal principal) {
        roomUserMapping.putIfAbsent(roomId, ConcurrentHashMap.newKeySet());
        roomUserMapping.get(roomId).add(principal);

        int userCount = roomUserMapping.get(roomId).size();
        /**
         *  When the join method is called,
         *  this line sends the updated user count to the /topic/chat/{roomId}/userCount topic.
         */
        messagingTemplate.convertAndSend("/topic/chat/" + roomId + "/userCount", userCount);
    }


    @MessageMapping("/chat/{roomId}/leave")
    public void leave(@DestinationVariable String roomId, Principal principal) {
        Set<Principal> users = roomUserMapping.get(roomId);
        if(users != null) {
            users.remove(principal);
            int userCount = users.size();
            messagingTemplate.convertAndSend("/topic/chat/" + roomId + "/userCount", userCount);
        }
    }

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/messages/{roomId}")
    public String handleChatMessage(@DestinationVariable String roomId, String message, Principal principal) {
        Set<Principal> users = roomUserMapping.get(roomId);
        if (users != null && users.contains(principal)) {
            log.info("Room: {}, message: {}", roomId, message);
            return message;
        } else {
            log.warn("User {} attempted to send a message to room {} without being a member", principal.getName(), roomId);
            return null;
        }
    }

}
