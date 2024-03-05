package org.example.feature.user.exception;

import org.example.exception.AbstractAlreadyExistsException;

public class UserAlreadyExistsException extends AbstractAlreadyExistsException {
    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
