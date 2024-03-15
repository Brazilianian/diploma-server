package org.example.feature.user.dto.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserCreateRequestDto(
        @Size(max = 255, message = "Довжина імені 255")
        @NotEmpty(message = "Ім'я не може бути порожнім")
        String firstName,

        @Size(max = 255, message = "Довжина прізвища 255")
        @NotEmpty(message = "Прізвище не може бути порожнім")
        String lastName,

        @Size(max = 255, message = "Довжина електронної пошти 255")
        @Email(message = "Електронна пошта повинна бути дійсною")
        @NotEmpty(message = "Електронна пошта не може бути порожньою")
        String email,

        @Size(min = 8, max = 255, message = "Довжина пароля повинна бути в межах від 8 до 255")
        @NotEmpty(message = "Пароль не може бути порожнім")
        String password
) {
}