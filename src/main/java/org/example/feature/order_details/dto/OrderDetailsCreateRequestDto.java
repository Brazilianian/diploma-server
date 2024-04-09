package org.example.feature.order_details.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.example.annotation.FutureOrPresent;
import org.example.feature.geo.point.dto.PointCreateRequestDto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDetailsCreateRequestDto(
        @Valid
        List<PointCreateRequestDto> points,
        @NotNull(message = "Дата прибуття не може бути пустою")
        String dateTimeFrom,
        @NotNull(message = "Дата прибуття не може бути пустою")
        String dateTimeTo,
        String distance,
        String duration
) {
}
