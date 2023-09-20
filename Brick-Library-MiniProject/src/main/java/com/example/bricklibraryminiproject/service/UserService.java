package com.example.bricklibraryminiproject.service;

import com.example.bricklibraryminiproject.exception.UserAlreadyExistsException;
import com.example.bricklibraryminiproject.exception.UserNotFoundException;
import com.example.bricklibraryminiproject.model.User;
import com.example.bricklibraryminiproject.model.request.LoginRequest;
import com.example.bricklibraryminiproject.repository.UserRepository;
import com.example.bricklibraryminiproject.security.JwtUtils;
import com.example.bricklibraryminiproject.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;


    /**
     * Constructor to initialize UserRepository, PasswordEncoder, JwtUtils, and AuthenticationManager
     *
     * @param userRepository         Repository for user-related database operations
     * @param passwordEncoder        Encoder for user password
     * @param jwtUtils               JWT utility for token generation and validation
     * @param authenticationManager Authentication manager for user authentication
     */
    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, JwtUtils jwtUtils, @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
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

    /**
     * Authenticates a user based on the provided login request and generates a JWT token upon successful authentication
     *
     * @param loginRequest The login request containing user credentials
     * @return An Optional containing the JWT token if authentication is successful; otherwise, an empty Optional
     */
    public Optional<String> authenticateAndGenerateToken(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmailAddress(),
                            loginRequest.getPassword()
                    )
            );
            // Set the authenticated user in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Get user details from the authenticated principal
            MyUserDetails myUserDetails = (MyUserDetails)  authentication.getPrincipal();

            // Generate a JWT token
            String jwtToken = jwtUtils.generateJwtToken(myUserDetails);

            // Validate the generated token before returning it
            if (jwtUtils.validateJwtToken(jwtToken)) {
                return Optional.of(jwtToken);
            } else {
                logger.warning("The token created is not valid for the user " + loginRequest.getEmailAddress());
                return Optional.empty();
            }
        } catch (Exception e) {
            logger.warning("Authentication failed for user: " + loginRequest.getEmailAddress());
            return Optional.empty();
        }
    }

    /**
     *
     * Updates a user's information based on the provided user ID and updated data
     *
     * @param id              The unique identifier of the user to update
     * @param updatedUserData The new user data to be applied
     * @return The updated user object
     * @throws UserNotFoundException If the user with the given ID is not found
     */
    public User updateUser(Long id, User updatedUserData) {
        // Check if the user with the given id exists
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            // Update the username field
            existingUser.setUsername(updatedUserData.getUsername());

            // Save the changes to the database
            userRepository.save(existingUser);
            logger.info("User updated with ID: " + id);

            return existingUser; // Return the updated user
        } else {
            logger.warning("User not found with ID: " + id);
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    /**
     * Deletes a user by their ID
     *
     * @param id The unique identifier of the user to delete
     * @throws UserNotFoundException If the user with the given ID is not found
     */
    public void deleteUser(Long id) {
        // Check if the user with the given id exists
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            // Delete the user from the database
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
    }


    public Optional<User> findByEmailAddress(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress);
    }
}
