package org.example.feature.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.example.feature.orderItem.dto.OrderItemCreateRequestDto;
import org.example.feature.order_details.dto.OrderDetailsCreateRequestDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record OrderCreateRequestDto(
        @NotNull(message = "Id of unit must be presented")
        UUID unitId,
        @Valid
        OrderDetailsCreateRequestDto orderDetailsCreateRequestDto,
        @NotEmpty(message = "Вкажіть назву замовлення")
        String name,
        List<OrderItemCreateRequestDto> items
) {
}
