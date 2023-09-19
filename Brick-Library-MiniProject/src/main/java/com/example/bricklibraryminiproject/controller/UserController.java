package com.example.bricklibraryminiproject.controller;

import com.example.bricklibraryminiproject.model.User;
import com.example.bricklibraryminiproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller handles user-related HTTP requests, such as user registration.
 */
@RestController
@RequestMapping("/auth/users")
public class UserController {

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
    @PostMapping("/register/") //http://localhost:9092/auth/users/register/
    public User registerUser(@RequestBody User userData) {
        return userService.registerNewUser(userData);
    }
}
