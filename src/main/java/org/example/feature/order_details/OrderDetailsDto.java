package org.example.feature.order_details;

import org.example.feature.geo.point.PointDto;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderDetailsDto(
        UUID id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        PointDto pointFrom,
        PointDto pointTo,
        LocalDateTime dateTimeTo,
        double distance
) {
}
