package org.example.feature.unit.dto.create;

import jakarta.validation.constraints.NotNull;
import org.example.feature.geo.point.dto.PointCreateRequestDto;

public record UnitCreateRequestDto(
        @NotNull(message = "Name of unit must be presented")
        String name,
        @NotNull(message = "Location of unit must be presented")
        PointCreateRequestDto location
) {
}
