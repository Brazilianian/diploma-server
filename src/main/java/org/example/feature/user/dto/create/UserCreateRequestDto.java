package org.example.feature.user.dto.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserCreateRequestDto(
        @Size(max = 255, message = "Length of first name is 255")
        @NotEmpty(message = "First name can't be empty")
        String firstName,

        @Size(max = 255, message = "Length of last name is 255")
        @NotEmpty(message = "Last name can't be empty")
        String lastName,

        @Size(max = 255, message = "Length of email is 255")
        @Email(message = "Email must be valid")
        @NotEmpty(message = "Email can't be empty")
        String email,

        @Size(min = 8, max = 255, message = "Length of password must be in bound of 8 and 255")
        @NotEmpty(message = "Password can't be empty")
        String password
) {
}