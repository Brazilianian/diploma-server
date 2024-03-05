package org.example.exception;

public class AbstractAlreadyExistsException extends AbstractException {
    public AbstractAlreadyExistsException() {
    }

    public AbstractAlreadyExistsException(String message) {
        super(message);
    }
}
