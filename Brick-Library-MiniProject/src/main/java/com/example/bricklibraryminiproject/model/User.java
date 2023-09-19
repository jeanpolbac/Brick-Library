package com.example.bricklibraryminiproject.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    private Long id;
    private String username;
    private String emailAddress;
    private String password;

}
