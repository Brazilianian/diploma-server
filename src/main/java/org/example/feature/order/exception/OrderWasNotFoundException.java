package org.example.feature.order.exception;

import org.example.exception.AbstractWasNotFoundException;

public class OrderWasNotFoundException extends AbstractWasNotFoundException {
    public OrderWasNotFoundException() {
    }

    public OrderWasNotFoundException(String message) {
        super(message);
    }
}
