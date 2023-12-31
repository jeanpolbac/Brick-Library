package com.example.bricklibraryminiproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {

    // Constructor that accepts the error message
    public UserAlreadyExistsException(String message) {
        super(message);
    }

}
