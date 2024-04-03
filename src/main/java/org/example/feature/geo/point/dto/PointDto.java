package org.example.feature.geo.point.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record PointDto(
        UUID id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        double latitude,
        double longitude
) {
}
