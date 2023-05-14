package com.example.socket_jpa_querydsl_test.chat;

import com.example.socket_jpa_querydsl_test.websocket.controller.ChatEndpoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Principal;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ChatEndpointTest {

    @Autowired
    private ChatEndpoint chatEndpoint;

    @MockBean
    private SimpMessagingTemplate messagingTemplate;

    private Principal mockPrincipal;

    @BeforeEach
    public void setup() {
        mockPrincipal = new UsernamePasswordAuthenticationToken("user", null);
    }

    @Test
    public void testJoin() {
        String roomId = "room1";
        chatEndpoint.join(roomId, mockPrincipal);
        verify(messagingTemplate, times(1)).convertAndSend("/topic/chat/" + roomId + "/userCount", 1);
    }

    @Test
    public void testLeave() {
        String roomId = "room1";
        chatEndpoint.join(roomId, mockPrincipal);
        reset(messagingTemplate);
        chatEndpoint.leave(roomId, mockPrincipal);
        verify(messagingTemplate, times(1)).convertAndSend("/topic/chat/" + roomId + "/userCount", 0);
    }

    @Test
    public void testHandleChatMessage() {
        String roomId = "room1";
        String message = "Hello";
        chatEndpoint.join(roomId, mockPrincipal);
        chatEndpoint.handleChatMessage(roomId, message, mockPrincipal);
    }

}
