package com.example.bricklibraryminiproject.security;

import com.example.bricklibraryminiproject.model.User;
import com.example.bricklibraryminiproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(MyUserDetailsService.class.getName());
    private final UserService userService;

    // Constructor-based dependency injection to provide an instance of UserService
    @Autowired
    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Load a user by its email address
     *
     * @param emailAddress The email address of the user to load
     * @return UserDetails The details of the user found
     * @throws UsernameNotFoundException if no user with the provided email address is found
     */
    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        logger.log(Level.INFO, "Trying to load user by email address: {0}", emailAddress);

        Optional<User> user = userService.findByEmailAddress(emailAddress);
        if (user.isEmpty()) {
            logger.log(Level.SEVERE, "User not found with email address: {0}", emailAddress);
            throw new UsernameNotFoundException("User not found with provided email address " + emailAddress);
        }
        logger.log(Level.INFO, "User with email address {0} found and being returned", emailAddress);
        return new MyUserDetails(user.get());
    }
}
