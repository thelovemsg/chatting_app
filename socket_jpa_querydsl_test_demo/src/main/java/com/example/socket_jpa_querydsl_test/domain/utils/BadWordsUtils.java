package com.example.socket_jpa_querydsl_test.domain.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component

public class BadWordsUtils {
    private static Set<String> badWords;

    static {
        try (InputStream inputStream = BadWordsUtils.class.getClassLoader().getResourceAsStream("bad_words.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            badWords = reader.lines().collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
            badWords = new HashSet<>();
        }
    }

    public static String maskingBadWords(String message) {
        String[] words = message.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            if (badWords.contains(words[i])) {
                words[i] = words[i].substring(0, 1) + "*".repeat(words[i].length() - 1);
            }
        }
        return String.join(" ", words);
    }

    public static boolean censorBadWords(String message) {
        return Arrays.stream(message.split("\\s+"))
                .anyMatch(word -> badWords.contains(word));
    }
}
