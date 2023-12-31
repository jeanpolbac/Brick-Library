package com.example.bricklibraryminiproject.controller;

import com.example.bricklibraryminiproject.model.User;
import com.example.bricklibraryminiproject.model.request.LoginRequest;
import com.example.bricklibraryminiproject.model.response.LoginResponse;
import com.example.bricklibraryminiproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * This controller handles user-related HTTP requests, such as user registration.
 */
@RestController
@RequestMapping("/auth/users")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    // Service to manage user data and operations
    private final UserService userService;

    /**
     * Constructor-based dependency injection of the UserService
     *
     * @param userService Service to handle user operations
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint to register a new user
     *
     * @param userData Details of the user to be registered
     * @return The registered user details
     */
    @PostMapping("/register/") //http://localhost:9022/auth/users/register/
    public User registerUser(@RequestBody User userData) {
        return userService.registerNewUser(userData);
    }


    /**
     * Authenticates a user based on the provided login request
     *
     * @param loginRequest The login request containing user credentials
     * @return A ResponseEntity with a LoginResponse containing a JWT token if authentication is successful, or an unauthorized response if authentication fails
     */
    @PostMapping("/login/") // http://localhost:9022/auth/users/login/
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        // Try to authenticate the user and generate a JWT token
        Optional<String> jwtToken = userService.authenticateAndGenerateToken(loginRequest);

        if (jwtToken.isPresent()) {
            logger.info("Authentication is good for user " + loginRequest.getEmailAddress());
            return  ResponseEntity.ok(new LoginResponse(jwtToken.get()));
        } else {
            logger.warning("Authentication failed for user " + loginRequest.getEmailAddress());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Authentication failed!"));
        }
    }


    /**
     * Endpoint to update a user's information based on the provided user ID and updated data
     *
     * @param id              The unique identifier of the user to update
     * @param updatedUserData The new user data to be applied
     * @return                The updated user object
     */
    @PutMapping("/edit/{id}/") // http://localhost:9022/auth/users/edit/{id}
    public User editUser(@PathVariable Long id, @RequestBody User updatedUserData) {
        return userService.updateUser(id, updatedUserData);
    }

    /**
     * Deletes a user by their ID
     *
     * @param id The unique identifier of the user to delete
     * @return A ResponseEntity indicating the result of the deletion operation
     */
    @DeleteMapping("/delete/{id}/")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User with ID " + id + " has been deleted.");
    }


}

