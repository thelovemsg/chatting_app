package com.example.socket_jpa_querydsl_test;

import com.example.socket_jpa_querydsl_test.domain.utils.BadWordsUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MessageMaskingTest {

    @Test
    @DisplayName("비속어 마스킹 테스트")
    public void badWordMaskingTest() {
        String message = "ㅅㅂ, this is a 씨발 test message!";
        String censoredMessage = BadWordsUtils.maskingBadWords(message);
        System.out.println(censoredMessage);
    }
}
