package org.example.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.validator.DateTimeValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateTimeValidator.class)
public @interface FutureOrPresent {
    String message() default "LocalDateTime must be present of future";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
