package com.example.socket_jpa_querydsl_test.domain.utils;

import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionUtils {

    private static String ALGORITHM = "AES";
    private final static String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String CHARSET_NAME = "UTF-8";

    /**
     *
     * @param originalPassword
     * @param key
     * @return
     * @description
     * The lengh of the key must not exceed over 16.
     * this encryption method use AES algorithm.
     */
    public static String encrypt(String originalPassword, String key) {
        try {
            String newKey = key.substring(0, 16);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKeySpec secretKey = new SecretKeySpec(newKey.getBytes(CHARSET_NAME), ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encrypted = cipher.doFinal(originalPassword.getBytes(CHARSET_NAME));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Failed to encrypt password", e);
        }
    }

    /**
     *
     * @param encodedPassword
     * @param key
     * @return
     * @description
     * The lengh of the key must not exceed over 16.
     * this encryption method use AES algorithm.
     */
    public static String decrypt(String encodedPassword, String key) {
        try {
            String newKey = key.substring(0, 16);
            SecretKeySpec secretKey = new SecretKeySpec(newKey.getBytes(CHARSET_NAME), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Failed to decrypt password", e);
        }
    }
}