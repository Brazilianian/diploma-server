package org.example.feature.auth.dto;

public record TokensDto(
        String accessToken,
        String refreshTokens
) {
}
