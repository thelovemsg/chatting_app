package com.example.socket_jpa_querydsl_test.websocket.config;

import com.example.socket_jpa_querydsl_test.domain.utils.BadWordsUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.ErrorHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final BadWordsUtils badWordsUtils;

    public WebSocketConfig(BadWordsUtils badWordsUtils) {
        this.badWordsUtils = badWordsUtils;
    }

    /**
     * 메시지 브로커 설정 역할
     * 클라이언트와 서버 사이에 메시지를 주고 받는데 책임을 짐.
     * @param config
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        /**
         * This line enables a simple, in-memory message broker with the specified destination prefixes ("/topic" and "/queue").
         * The broker is responsible for forwarding messages to clients that have subscribed to a matching destination.
         * The "/topic" prefix is typically used for broadcasting messages to multiple clients,
         * while "/queue" is often used for point-to-point messaging.
         */
        config.enableSimpleBroker("/topic", "/queue");


        /**
         * This line configures the destination prefix for messages that should be processed by the application
         * (i.e., handled by @MessageMapping-annotated methods in your controllers).
         * When a client sends a message with a destination starting with "/app",
         * the server will route it to the corresponding @MessageMapping-annotated method in your application.
         */
        config.setApplicationDestinationPrefixes("/app");

        /**
         * This line configures the destination prefix for user-specific messages.
         * When a client sends a message with a destination starting with "/user",
         * the server will route it to the appropriate user session.
         * This is useful for sending private messages to a specific user or sending messages to a user across multiple sessions
         * (e.g., if the user is connected from multiple devices).
         */
        config.setUserDestinationPrefix("/user");
    }

    /**
     * STOMP 엔드포인트의 역할 담당.
     * STOMP는 WebSocket의 위에서 작동하는 simple messagin protocol이다.
     * client와 server간에 communication 메커니즘을 제공한다.
     *
     * @param registry
     * @description
     * In the WebSocketConfig class, you have registered a STOMP endpoint
     * ("/chat-websocket") with the registerStompEndpoints method.
     * Clients can connect to this endpoint to establish a WebSocket connection.
     *
     * The configureMessageBroker method defines destination prefixes for the message broker
     * ("/topic" and "/queue") and application ("/app").
     * The /app prefix is used for routing messages to the
     * MessageMapping-annotated methods in your controllers.
     * The /topic and /queue prefixes are used by the message broker to send messages to
     * clients that have subscribed to a matching destination.
     * The /user prefix is used for user-specific messages.
     *
     * With this configuration, clients can connect to the server,
     * subscribe to topics, and send messages to the designated destinations.
     * The MessageMapping-annotated methods in your controllers will
     * handle these messages and perform the required actions,
     * such as updating the user count, sending chat messages to the correct room, and so on.
     *
     * For example, when a client sends a message to /app/chat/{roomId}/join,
     * the join method in the ChatEndpoint class will be invoked, and it will
     * update the user count and broadcast the updated count to the
     * /topic/chat/{roomId}/userCount topic. Clients that have subscribed
     * to this topic will receive the updated user count.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //새로운 STOMP endpoint 를 등록. client는 여기에 접속할거임.   
        registry.addEndpoint("/chat-websocket").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new FilterChannelInterceptor(badWordsUtils));
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.addDecoratorFactory(CustomWebSocketHandlerDecorator::new);
    }

}
