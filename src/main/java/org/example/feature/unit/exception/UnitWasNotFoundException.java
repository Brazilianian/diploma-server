package org.example.feature.unit.exception;

import org.example.exception.AbstractWasNotFoundException;

public class UnitWasNotFoundException extends AbstractWasNotFoundException {
    public UnitWasNotFoundException() {
    }

    public UnitWasNotFoundException(String message) {
        super(message);
    }
}
