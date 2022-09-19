package com.serasa.desafio.utility;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtility implements Serializable {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private static final long serialVersionUID = -2467444267159966469L;
    @Value("${jwt.secret}")
    private String secretKey;

    private Date localToDate(final LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Claims parseToken(final String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public String generateToken(final UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(localToDate(LocalDateTime.now()))
                .setExpiration(localToDate(LocalDateTime.now().plusSeconds(JWT_TOKEN_VALIDITY)))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public boolean isTokenValid(final Claims token) {
        return token.getExpiration()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
                .isAfter(LocalDateTime.now());
    }
}
