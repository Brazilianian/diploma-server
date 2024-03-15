package org.example.feature.auth.dto;

public record AuthRequestDto(
        String email,
        String password
) {
}
