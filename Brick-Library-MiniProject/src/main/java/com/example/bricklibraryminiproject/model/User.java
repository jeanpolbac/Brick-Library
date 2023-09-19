package com.example.bricklibraryminiproject.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    // Primary key of the User entity
    @Id
    // Id property is mapped to database column
    @Column
    // How the primary key will be generated
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Username field mapped to database column
    @Column
    private String username;

    // Email set to be unique, cannot exist more than once. Email field mapped to database column
    @Column(unique = true)
    private String emailAddress;

    // Password field mapped to database column
    @Column
    private String password;


    // Default Constructor
    public User() {
    }

    /**
     * Constructs a User object with the provided parameters
     *
     * @param id            Unique identifier for the user
     * @param username      Display name of the user
     * @param emailAddress  Email address of the user
     * @param password      Password associated with the user account
     */
    public User(Long id, String username, String emailAddress, String password) {
        this.id = id;
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    // Getter and Setter methods for the User class attributes
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Customized toString method for structured output.
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
