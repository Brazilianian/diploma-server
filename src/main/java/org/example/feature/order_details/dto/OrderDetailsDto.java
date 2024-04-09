package org.example.feature.order_details.dto;

import org.example.feature.geo.point.dto.PointDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderDetailsDto(
        UUID id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<PointDto> points,
        LocalDate dateTimeFrom,
        LocalDate dateTimeTo,
        String distance,
        String duration
) {
}
