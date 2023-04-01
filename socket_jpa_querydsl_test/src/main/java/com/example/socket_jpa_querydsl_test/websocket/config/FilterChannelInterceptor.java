package com.example.socket_jpa_querydsl_test.websocket.config;

import com.example.socket_jpa_querydsl_test.domain.utils.BadWordsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.GenericMessage;

import java.nio.charset.StandardCharsets;

@Slf4j
public class FilterChannelInterceptor implements ChannelInterceptor {

    private final BadWordsUtils badWordsUtils;

    public FilterChannelInterceptor(BadWordsUtils badWordsUtils) {
        this.badWordsUtils = badWordsUtils;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        Object payload = message.getPayload();
        if (payload instanceof byte[]) {
            String originalContent = new String((byte[]) payload, StandardCharsets.UTF_8);
            String sendingContent = badWordsUtils.censorBadWords(originalContent)
                                        ? badWordsUtils.maskingBadWords(originalContent) : originalContent;
            Message<?> censoredMessage = new GenericMessage<>(sendingContent, message.getHeaders());
            return ChannelInterceptor.super.preSend(censoredMessage, channel);
        }
        return ChannelInterceptor.super.preSend(message, channel);
    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        log.info("afterSendCompletion mssage={} channel={}", message, channel);
        ChannelInterceptor.super.afterSendCompletion(message, channel, sent, ex);
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        log.info("postSend mssage={} channel={}", message, channel);
        ChannelInterceptor.super.postSend(message, channel, sent);
    }

    @Override
    public boolean preReceive(MessageChannel channel) {
        log.info("preReceive channel={}", channel);
        return ChannelInterceptor.super.preReceive(channel);
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        log.info("postReceive mssage={} channel={}", message, channel);
        return ChannelInterceptor.super.postReceive(message, channel);
    }

    @Override
    public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
        log.info("afterReceiveCompletion mssage={} channel={}", message, channel);
        ChannelInterceptor.super.afterReceiveCompletion(message, channel, ex);
    }
}
