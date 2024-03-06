package org.example.feature.unit.exception;

import org.example.exception.AbstractAlreadyExistsException;

public class UnitAlreadyExistsException extends AbstractAlreadyExistsException {
    public UnitAlreadyExistsException() {
    }

    public UnitAlreadyExistsException(String message) {
        super(message);
    }
}
