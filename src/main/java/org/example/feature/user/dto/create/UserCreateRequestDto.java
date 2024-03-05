package org.example.feature.user.dto.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserCreateRequestDto(
        @Size(max = 255, message = "Length of first name is 255")
        String firstName,

        @Size(max = 255, message = "Length of last name is 255")
        String lastName,

        @Size(max = 255, message = "Length of email is 255")
        @Email(message = "Email must be valid")
        String email,

        @Size(min = 8, max = 255, message = "Length of password must be in bound of 8 and 255")
        String password
) {
}