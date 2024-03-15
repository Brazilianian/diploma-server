package org.example.handler;

import org.example.dto.ExceptionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class IllegalArgumentExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(IllegalArgumentExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> catchIllegalArgumentException(IllegalArgumentException ex) {
        LOGGER.warn(ex.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
