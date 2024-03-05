package org.example.feature.user.exception;

import org.example.exception.AbstractWasNotFoundException;

public class UserWasNotFoundException extends AbstractWasNotFoundException {
    public UserWasNotFoundException(String message) {
        super(message);
    }
}