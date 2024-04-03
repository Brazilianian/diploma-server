package org.example.feature.image.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ImageDto(
        UUID id,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
