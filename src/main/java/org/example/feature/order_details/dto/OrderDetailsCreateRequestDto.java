package org.example.feature.order_details.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.example.annotation.FutureOrPresent;
import org.example.feature.geo.point.dto.PointCreateRequestDto;

import java.time.LocalDateTime;

public record OrderDetailsCreateRequestDto(
        @NotNull(message = "Вкажіть точку старту")
        @Valid
        PointCreateRequestDto pointFrom,
        @NotNull(message = "Вкажіть місце прибуття")
        @Valid
        PointCreateRequestDto pointTo,
        @NotNull(message = "Дата прибуття не може бути пустою")
        @FutureOrPresent(message = "Дата на може бути минулою")
        LocalDateTime dateTimeTo
) {
}
