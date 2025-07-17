package com.ecommercenext.nextecommerce.util;

import org.springframework.stereotype.Component;

import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtUtil {
    private static final String SECRET = "a8$Dk1@uBz!X9qPz72vD!m8eW0lTgYzF";
    private static final long EXPIRATION_TIME = 86400000;

    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public String validateToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token)
                .getSubject();
    }
}

