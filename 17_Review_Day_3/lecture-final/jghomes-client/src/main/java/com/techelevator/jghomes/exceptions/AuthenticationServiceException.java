package com.techelevator.jghomes.exceptions;

public class AuthenticationServiceException extends Exception {
    private static final long serialVersionUID = 1L;

    public AuthenticationServiceException(String message) {
        super(message);
    }
}
