package com.example.bricklibraryminiproject.service;

import com.example.bricklibraryminiproject.exception.UserAlreadyExistsException;
import com.example.bricklibraryminiproject.model.User;
import com.example.bricklibraryminiproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(User userData) {
        if (userRepository.isEmailRegistered(userData.getEmailAddress())) {

            logger.warning("An attempt to register with existing email, " + userData.getEmailAddress() + ", occurred.");
            throw new UserAlreadyExistsException("Email, " + userData.getEmailAddress() + ", is already registered.");
        }
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        logger.info("User with email, " + userData.getEmailAddress() + ", has successfully registered.");

        return userRepository.save(userData);
    }
}
