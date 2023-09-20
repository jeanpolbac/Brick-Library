package com.example.bricklibraryminiproject.security;

import com.example.bricklibraryminiproject.model.User;
import com.example.bricklibraryminiproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(MyUserDetailsService.class.getName());
    private final UserService userService;

    @Autowired
    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        Optional<User> user = userService.findByEmailAddress(emailAddress);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with provided email address " + emailAddress);
        }
        return new MyUserDetails(user.get());
    }
}
