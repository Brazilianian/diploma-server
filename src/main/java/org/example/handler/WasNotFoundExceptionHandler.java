package org.example.handler;

import org.example.dto.ExceptionDto;
import org.example.exception.AbstractWasNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WasNotFoundExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(WasNotFoundExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> catchAbstractWasNotFoundException(AbstractWasNotFoundException ex) {
        LOGGER.warn(ex.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
