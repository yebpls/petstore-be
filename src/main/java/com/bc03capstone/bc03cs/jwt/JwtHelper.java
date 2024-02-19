package com.bc03capstone.bc03cs.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtHelper {
    @Value("${token.key}")
    private String strKey;

    private long expiredTime = 60L*24*60*60*1000;
    public String generateToken (String data, String roles, int id) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
        Date date = new Date();
        long futureMilis = date.getTime() + expiredTime;
        Date futureDate = new Date(futureMilis);
        return Jwts.builder().setSubject(data)
            .claim("roles", roles)
            .claim("id", id)// Add roles as a claim
            .setExpiration(futureDate)
            .signWith(key)
            .compact();
    }

    public String decodeToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
        String data = null;
        try {
            data = Jwts.parser().verifyWith(key).build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return data;
    }
}
