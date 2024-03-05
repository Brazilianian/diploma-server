package org.example.exception;

abstract public class AbstractException extends RuntimeException {
    public AbstractException() {
    }

    public AbstractException(String message) {
        super(message);
    }
}
