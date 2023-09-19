package com.example.bricklibraryminiproject.repository;

import com.example.bricklibraryminiproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Spring Data JPA repository interface
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a User entity by its email address
     *
     * @param emailAddress The email address associated with a user
     * @return An Optional containing the User entity if found, or an empty Optional if no User is found with the given email address.
     */
    Optional<User> findByEmailAddress(String emailAddress);


    /**
     * Checks if a User entity with the given email address exists
     *
     * @param emailAddress The email address to check
     * @return true if a User entity with the given email address exists, false otherwise
     */

    boolean isEmailRegistered(String emailAddress);

}
