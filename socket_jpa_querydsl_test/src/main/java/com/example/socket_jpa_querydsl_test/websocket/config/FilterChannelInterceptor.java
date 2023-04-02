package com.example.socket_jpa_querydsl_test.websocket.config;

<<<<<<< Updated upstream
import com.example.socket_jpa_querydsl_test.domain.utils.BadWordsUtils;
=======
import com.example.socket_jpa_querydsl_test.websocket.utils.WordCheckUtils;
>>>>>>> Stashed changes
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.GenericMessage;

<<<<<<< Updated upstream
import java.nio.charset.StandardCharsets;
=======
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
>>>>>>> Stashed changes

@Slf4j
public class FilterChannelInterceptor implements ChannelInterceptor {

<<<<<<< Updated upstream
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
=======
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        String payload = (String) message.getPayload();
        Matcher matcher = WordCheckUtils.loadBadWordPattern().matcher(payload);

        if(matcher.find()){
            String result = matcher.replaceAll(matchResult -> {
                String word = matchResult.group();
                int length = word.length();
                if (length <= 2) {
                    return "*".repeat(length);
                } else {
                    return word.substring(0, 1) + "*".repeat(length - 2) + word.substring(length - 1);
                }
            });
            return new GenericMessage<>(result, message.getHeaders());
        }
        return message;
>>>>>>> Stashed changes
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

}
