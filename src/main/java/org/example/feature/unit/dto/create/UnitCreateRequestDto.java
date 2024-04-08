package org.example.feature.unit.dto.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.example.feature.geo.point.dto.PointCreateRequestDto;
import org.example.feature.image.dto.ImageCreateRequestDto;
import org.example.feature.image.dto.ImageDto;

public record UnitCreateRequestDto(
        @NotNull(message = "Назва підрозділу не може бути пустою")
        String name,
        String description,
        @Valid
        @NotNull(message = "Вкажіть місцезнаходження частини")
        PointCreateRequestDto location,

        @NotNull(message = "Вкажіть зображення")
        @Valid
        ImageCreateRequestDto image
) {
}
