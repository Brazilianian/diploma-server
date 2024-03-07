package org.example.feature.order_details.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.example.annotation.FutureOrPresent;
import org.example.feature.geo.point.dto.PointCreateRequestDto;

import java.time.LocalDateTime;

public record OrderDetailsCreateRequestDto(
        @NotNull(message = "Start location must be present")
        @Valid
        PointCreateRequestDto pointFrom,
        @NotNull(message = "Finish location must be presented")
        @Valid
        PointCreateRequestDto pointTo,
        @NotNull(message = "Deadline date must be presented")
        @FutureOrPresent(message = "Deadline date can't be in past")
        LocalDateTime dateTimeTo
) {
}
