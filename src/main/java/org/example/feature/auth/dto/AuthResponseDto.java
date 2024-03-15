package org.example.feature.auth.dto;

public record AuthResponseDto(
        String accessToken,
        String refreshToken
) {
}
