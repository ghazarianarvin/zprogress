package com.zprogress.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JwtTokenService {
    Logger logger = LoggerFactory.getLogger(JwtTokenService.class);

    private static final Key SIGNATURE_KEY = MacProvider.generateKey(SignatureAlgorithm.HS256);

    private static final Map<String, String> JSON_WEB_TOKENS = new ConcurrentHashMap<>();

    public String getToken(String username) {
        if (JSON_WEB_TOKENS.containsKey(username)) {
            var token = JSON_WEB_TOKENS.get(username);
            if (token != null && isTokenValid(token)) {
                return token;
            }
        }
        return createNewToken(username);
    }

    public boolean isTokenValid(String token) {
        try {
            var claims = Jwts.parser()
                    .setSigningKey(SIGNATURE_KEY).parseClaimsJws(token);
            var expirationDate = claims.getBody().getExpiration();
            if (expirationDate.before(Date.from(Instant.now()))) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            logger.info("Expired or invalid JWT token, {}", e.getMessage());
            return false;
        }
    }

    public String extractUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNATURE_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean discardTokenFor(String username) {
        return JSON_WEB_TOKENS.remove(username) != null;
    }

    private String createNewToken(String username) {
        var claims = Jwts.claims()
                .setSubject(username);
        var now = Date.from(Instant.now());
        var validity = new Date(now.getTime() + 36_000_000);
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, SIGNATURE_KEY)
                .compact();
        JSON_WEB_TOKENS.put(username, token);
        return token;
    }

}
