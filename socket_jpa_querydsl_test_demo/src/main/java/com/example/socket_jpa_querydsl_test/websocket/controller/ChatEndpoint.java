package com.example.socket_jpa_querydsl_test.websocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
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

    @MessageMapping("/chat/{roomId}/deleteMessage")
    public void deleteMessage(@DestinationVariable String roomId, String messageId, Principal principal) {
//        Message message = messageRepository.findById(messageId);
//        if (message != null && message.getSender().equals(principal.getName())) {
//            messageRepository.delete(message);
//            messagingTemplate.convertAndSend("/topic/chat/" + roomId + "/deletedMessage", messageId);
//        } else {
//            log.warn("User {} attempted to delete a message they didn't send or that doesn't exist", principal.getName());
//        }
    }


    @MessageMapping("/chat/{roomId}/read/{messageId}")
    public void handleMessageRead(@DestinationVariable String roomId, @DestinationVariable String messageId, Principal principal) {
//        Set<Principal> usersWhoRead = messageReadUserMapping.get(messageId);
//        if (usersWhoRead != null) {
//            usersWhoRead.add(principal);
//
//            // Create or update a MessageReadReceipt for this user and message
//            MessageReadReceipt receipt = messageReadReceiptService.createOrUpdateReceipt(principal.getName(), messageId);
//            // Broadcast the updated receipt to a specific topic
//            messagingTemplate.convertAndSend("/topic/chat/" + roomId + "/readReceipts/" + messageId, receipt);
//        } else {
//            log.warn("User {} attempted to mark as read a non-existing message {}", principal.getName(), messageId);
//        }
    }

}
