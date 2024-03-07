package org.example.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.annotation.FutureOrPresent;

import java.time.LocalDateTime;

public class DateTimeValidator implements ConstraintValidator<FutureOrPresent, LocalDateTime> {
    @Override
    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
        if (localDateTime == null) {
            return false;
        }

        LocalDateTime now = LocalDateTime.now();
        return localDateTime.isAfter(now) || localDateTime.isEqual(now);
    }
}
