package com.example.bricklibraryminiproject.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Logger;

@Service
public class JwtUtils {
    private static final Logger logger = Logger.getLogger(JwtUtils.class.getName());


    @Value("${jwt-secret}")
    private String jwtSecretKey;

    @Value("${jwt-expiration-ms}")
    private long jwtTokenExpiration;


    /**
     * Generates a JWT token based on user details
     *
     * @param userDetails The user details used to create the token
     * @return A JWT token as a String
     */
    public String generateJwtToken(MyUserDetails userDetails) {
        return Jwts.builder()
                // Set the subject (usually the username)
                .setSubject(userDetails.getUsername())

                // Set the token's issuance time
                .setIssuedAt(new Date(System.currentTimeMillis()))

                // Set the token's expiration time
                .setExpiration(new Date(System.currentTimeMillis() + jwtTokenExpiration))

                // Sign the token with the secret key using SHA-256
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)

                // Compact and return the JWT token as a String
                .compact();
    }
}
