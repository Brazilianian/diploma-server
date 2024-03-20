package org.example.feature.user.dto;

import org.example.feature.user.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(
        UUID id,
        String firstName,
        String lastName,
        Role role,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
