package com.security.OAuth2;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {

    private static String SECRET;
    private static long EXPIRATION;

    @Value("${app.jwt.secret}")
    public void setSecret(String secret) { SECRET = secret; }

    @Value("${app.jwt.expiration-ms}")
    public void setExpiration(long expiration) { EXPIRATION = expiration; }

    public static String generateToken(OidcUser user) {

        // 1. Get the bytes of your secret string
        byte[] keyBytes = SECRET.getBytes(StandardCharsets.UTF_8);

        // 2. Create the SecretKey object (This will throw an error if < 32 chars)
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        String JJWT =  Jwts.builder()
                .subject(user.getEmail())
                .claim("name", user.getFullName())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key)
                .compact();

        System.out.println("Generated JWT Token :" + JJWT);
        return JJWT;
    }
}