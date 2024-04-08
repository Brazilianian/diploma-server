package org.example.feature.image.dto;

import jakarta.validation.constraints.NotNull;

public record ImageCreateRequestDto(
        @NotNull(message = "Вкажіть зображення")
        String content
) {
}
