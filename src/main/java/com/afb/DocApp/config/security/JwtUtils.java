package com.afb.DocApp.config.security;

import com.afb.DocApp.domain.model.User;
import com.afb.DocApp.domain.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {
    @Value("${docapp.jwt.expirationTime}")
    private Long expirationTime;

    @Value("${docapp.jwt.secret}")
    private String secret;

    @Value("${docapp.jwt.issuer}")
    private String issuer;

    @Autowired
    private UserRepository userRepository;

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        User user = userRepository.findByEmail(userDetails.getUsername());
        String userId = String.valueOf(user.getId());
        return createToken(claims, userId);
    }

    private String createToken(Map<String, Object> claims, String userId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiration = now.plusMinutes(expirationTime);
        return Jwts.builder()
                .setIssuer(issuer)
                .setClaims(claims)
                .setSubject(userId)
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
