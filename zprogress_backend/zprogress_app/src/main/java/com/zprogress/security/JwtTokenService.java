package com.zprogress.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

public class JwtTokenService {

    private static final Key SIGNATURE_KEY = MacProvider.generateKey(SignatureAlgorithm.HS256);

    public String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        Date now = Date.from(Instant.now());
        Date validity = new Date(now.getTime() + 36_000_000);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, SIGNATURE_KEY)
                .compact();
    }

    public String extractUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNATURE_KEY).parseClaimsJws(token).getBody().getSubject();
    }

}
