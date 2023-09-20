package com.example.bricklibraryminiproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * This configuration class is responsible for setting up the global security configurations
 * for the application, such as URL-based security and method-level security.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    /**
     * Defines the primary security filter chain for the application. This method sets up
     * the URL-based security configurations
     *
     * @param http The HttpSecurity object, which allows customizing the security configurations
     * @return SecurityFilterChain representing the security settings
     * @throws Exception if there's an error setting up the security configurations
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/h2-console/**", "/auth/users/**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement() // manage user session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
                .headers().frameOptions().disable(); // allow to display iframe.
        return http.build();
    }


    /**
     * Defines a Spring Bean for configuring and providing the AuthenticationManager
     *
     * @param authConfig The AuthenticationConfiguration to obtain the AuthenticationManager
     * @return An instance of AuthenticationManager configured by AuthenticationConfiguration
     * @throws Exception If there's an error obtaining the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }

    /**
     * Provides an instance of BCryptPasswordEncoder
     * This is used for encoding/hashing passwords before storing them
     *
     * @return a BCryptPasswordEncoder instance
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
