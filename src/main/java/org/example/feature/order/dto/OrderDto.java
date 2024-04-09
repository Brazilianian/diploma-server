package org.example.feature.order.dto;

import org.example.feature.order.OrderStatus;
import org.example.feature.orderItem.dto.OrderItemDto;
import org.example.feature.order_details.dto.OrderDetailsDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderDto(
        UUID id,
        UUID unitId,
        String name,
        List<OrderItemDto> items,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        OrderStatus orderStatus,
        OrderDetailsDto orderDetailsDto
) {
}
