package com.example.bricklibraryminiproject.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class JwtUtils {
    private static final Logger logger = Logger.getLogger(JwtUtils.class.getName());


    @Value("${jwt-secret}")
    private String jwtSecretKey;

    @Value("${jwt-expiration-ms}")
    private String jwtTokenExpiration;


}
