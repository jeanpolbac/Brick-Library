package com.example.bricklibraryminiproject.security;

import com.example.bricklibraryminiproject.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


/**
 * Custom implementation of Spring Security's UserDetails interface.
 */
public class MyUserDetails implements UserDetails {

    private final User user;

    /**
     * Constructor that initializes the MyUserDetails with a User entity
     *
     * @param user the User entity
     */
    public MyUserDetails(User user) {
        this.user = user;
    }


    /**
     * Returns the authorities granted to the user
     * Every user is assigned the 'ROLE_USER' authority in the Dev environment
     *
     * @return a collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * Returns the password used to authenticate the user
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }


    /**
     * Returns the username used to authenticate the user
     *
     * @return the username
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }


    /**
     * Indicates whether the user's account has expired
     *
     * @return true, because accounts never expire in the Dev environment
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    /**
     * Indicates whether the user is locked or unlocked
     *
     * @return true, because users are never locked in the Dev environment
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    /**
     * Indicates whether the user's credentials (password) has expired
     *
     * @return true, because credentials never expire in the Dev environment
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled
     *
     * @return true, because users are always enabled in the Dev environment
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Returns the underlying User entity
     *
     * @return the User entity
     */
    public User getUser() {
        return user;
    }
}
