package com.example.socket_jpa_querydsl_test.websocket.utils;

import com.example.socket_jpa_querydsl_test.websocket.config.FilterChannelInterceptor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordCheckUtils {

    private static final Pattern BAD_WORD_PATTERN;

    static {
        BAD_WORD_PATTERN = loadBadWordPattern();
    }

    public static Pattern loadBadWordPattern() {
        // try catch resource 적용
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                FilterChannelInterceptor.class.getResourceAsStream("/bad_words.txt"), StandardCharsets.UTF_8))) {
            List<String> badWords = reader.lines().collect(Collectors.toList());
            String patternString = badWords.stream().map(Pattern::quote).collect(Collectors.joining("|"));
            return Pattern.compile("(?i)\\b(" + patternString + ")\\b");
        } catch (Throwable e) {
            throw new RuntimeException("Failed to load bad words", e);
        }
    }
}
