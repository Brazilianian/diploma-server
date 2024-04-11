package org.example.feature.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.example.feature.image.dto.ImageCreateRequestDto;

public record UserUpdateRequestDto(
        @Size(max = 255, message = "Довжина імені 255")
        @NotEmpty(message = "Ім'я не може бути порожнім")
        String firstName,

        @Size(max = 255, message = "Довжина прізвища 255")
        @NotEmpty(message = "Прізвище не може бути порожнім")
        String lastName,

        ImageCreateRequestDto image
) {
}
