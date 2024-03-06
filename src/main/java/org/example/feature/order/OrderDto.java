package org.example.feature.order;

import org.example.feature.order_details.OrderDetailsDto;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderDto(
        UUID id,
        UUID unitId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        OrderStatus orderStatus,
        OrderDetailsDto orderDetailsDto
) {
}
