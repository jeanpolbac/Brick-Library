package com.example.bricklibraryminiproject.service;

import com.example.bricklibraryminiproject.exception.UserAlreadyExistsException;
import com.example.bricklibraryminiproject.model.User;
import com.example.bricklibraryminiproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(User userData) {
        if (userRepository.isEmailRegistered(userData.getEmailAddress())) {
            throw new UserAlreadyExistsException("Email, " + userData.getEmailAddress() + ", is already registered.");
        }
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));

        return userRepository.save(userData);
    }
}
