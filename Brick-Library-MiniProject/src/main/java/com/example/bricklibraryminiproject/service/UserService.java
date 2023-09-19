package com.example.bricklibraryminiproject.service;

import com.example.bricklibraryminiproject.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

   private final PasswordEncoder passwordEncoder;


}
