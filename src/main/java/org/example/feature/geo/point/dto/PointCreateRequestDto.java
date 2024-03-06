package org.example.feature.geo.point.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record PointCreateRequestDto(
        @Min(value = -90, message = "Latitude must be in rage of [-90; 90]")
        @Max(value = 90, message = "Latitude must be in rage of [-90; 90]")
        double latitude,
        @Min(value = -90, message = "Longitude must be in rage of [-90; 90]")
        @Max(value = 90, message = "Longitude must be in rage of [-90; 90]")
        double longitude
) {
}
