package com.example.socket_jpa_querydsl_test.websocket.config;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

public class CustomWebSocketHandlerDecorator extends WebSocketHandlerDecorator {

    public CustomWebSocketHandlerDecorator(WebSocketHandler delegate) {
        super(delegate);
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // Log the error
        System.err.println("WebSocket transport error: " + exception.getMessage());

        // Close the WebSocket session with an error status
        session.close(CloseStatus.SERVER_ERROR);

        // Call the base class's error handling method
        super.handleTransportError(session, exception);
    }
}
