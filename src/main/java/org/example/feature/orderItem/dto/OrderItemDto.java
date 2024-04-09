package org.example.feature.orderItem.dto;

import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderItemDto(
        UUID id,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
