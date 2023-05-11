package com.example.xowrld.Config;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;


@Component
public class TokenGenerator {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateToken() {
        byte[] token = new byte[6];
        secureRandom.nextBytes(token);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(token);
    }
}