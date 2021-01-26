package com.sam.takenote.helper;

import com.sam.takenote.exception.TakeNoteGenericException;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.sam.takenote.exception.TakeNoteErrorCodes.CLIENT_TOKEN_ERROR;

@Component
public class JWTHelper {

    private final String SECRET_KEY = "secret_key";

    private final int TOKEN_EXPIRY_MINUTES = 20;

    public String generateToken(String username) {
        try {
            Map<String, Object> claims = new HashMap<>();
            return createToken(claims, username);
        } catch (Exception e) {
            throw new TakeNoteGenericException("Token cannot be generated");
        }
    }

    public Boolean validateToken(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return (username.equals(tokenUsername) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new TakeNoteGenericException("Token is expired", CLIENT_TOKEN_ERROR);
        } catch (SignatureException e) {
            throw new TakeNoteGenericException("Token signature is invalid", CLIENT_TOKEN_ERROR);
        }
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private String createToken(Map<String, Object> claims, String subject) throws UnsupportedEncodingException {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Duration.ofMinutes(TOKEN_EXPIRY_MINUTES).toMillis()))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes(StandardCharsets.UTF_8)).compact();
    }
}
