package org.example.feature.user.dto;

import org.example.feature.user.Role;

import java.util.UUID;

public record UserValidateDto(
        UUID id,
        String email,
        Role role
) {
}