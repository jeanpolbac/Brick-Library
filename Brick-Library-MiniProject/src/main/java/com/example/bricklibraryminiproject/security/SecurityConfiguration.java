package com.example.bricklibraryminiproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return http.build();
    }
}
