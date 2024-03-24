package org.example.feature.unit.dto.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.example.feature.geo.point.dto.PointCreateRequestDto;

public record UnitCreateRequestDto(
        @NotNull(message = "Назва підрозділу не може бути пустою")
        String name,
        @Valid
        @NotNull(message = "Вкажіть місцезнаходження частини")
        PointCreateRequestDto location
) {
}
