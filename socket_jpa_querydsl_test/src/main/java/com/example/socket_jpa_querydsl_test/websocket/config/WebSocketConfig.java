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
        config.enableSimpleBroker("/topic", "/queue");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    /**
     * STOMP 엔드포인트의 역할 담당.
     * STOMP는 WebSocket의 위에서 작동하는 simple messagin protocol이다.
     * client와 server간에 communication 메커니즘을 제공한다.
     *
     * @param registry
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
