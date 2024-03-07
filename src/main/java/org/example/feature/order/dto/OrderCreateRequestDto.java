package org.example.feature.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.example.feature.order_details.dto.OrderDetailsCreateRequestDto;

import java.util.UUID;

public record OrderCreateRequestDto(
        @NotNull(message = "Id of unit must be presented")
        UUID unitId,
        @Valid
        OrderDetailsCreateRequestDto orderDetailsCreateRequestDto
) {
}
