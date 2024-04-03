package org.example.handler;

import org.example.dto.ValidationDto;
import org.example.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<ValidationDto> catchValidationException(ValidationException ex) {
        LOGGER.warn(
                String.format("%s. %s", ex.getMessage(), ex.getErrors())
        );
        return new ResponseEntity<>(
                new ValidationDto(ex.getMessage(), ex.getErrors()),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }
}
