package com.example.bricklibraryminiproject.service;

import com.example.bricklibraryminiproject.exception.UserAlreadyExistsException;
import com.example.bricklibraryminiproject.model.User;
import com.example.bricklibraryminiproject.model.request.LoginRequest;
import com.example.bricklibraryminiproject.repository.UserRepository;
import com.example.bricklibraryminiproject.security.JwtUtils;
import com.example.bricklibraryminiproject.security.MyUserDetails;
import jdk.jshell.spi.ExecutionControlProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * Service class responsible for user-related operations
 */
@Service
public class UserService {

    // Logger instance to log events related to UserService operations
    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    // Repository to interact with user data in the database
    private final UserRepository userRepository;

    // Encoder for password encryption
    private final PasswordEncoder passwordEncoder;

    private JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;


    /**
     * Constructor to initialize UserRepository and PasswordEncoder
     *
     * @param userRepository        Repository for user-related database operations
     * @param passwordEncoder       Encoder for user password
     */
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * Registers a new user
     *
     * @param userData User data to register
     * @return The registered user
     * @throws UserAlreadyExistsException if a user with the same email already exists
     */
    public User registerNewUser(User userData) {
        if (userRepository.existsByEmailAddress(userData.getEmailAddress())) {

            logger.warning("An attempt to register with existing email, " + userData.getEmailAddress() + ", occurred.");
            throw new UserAlreadyExistsException("Email, " + userData.getEmailAddress() + ", is already registered.");
        }
        // Encrypt the user password before saving to the database
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        logger.info("User with email, " + userData.getEmailAddress() + ", has successfully registered.");

        // Save the user data in the database
        return userRepository.save(userData);
    }

    public Optional<String> authenticateAndGenerateToken(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmailAddress(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            MyUserDetails myUserDetails = (MyUserDetails)  authentication.getPrincipal();
            return Optional.of(jwtUtils.generateJwtToken(myUserDetails));
        } catch (Exception e) {
            logger.warning("Authentication failed for user: " + loginRequest.getEmailAddress());
            return Optional.empty();
        }
    }

    public Optional<User> findByEmailAddress(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress);
    }
}
