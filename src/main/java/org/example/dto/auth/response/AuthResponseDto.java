package org.example.dto.auth.response;

public record AuthResponseDto(
        String accessToken,
        String refreshToken
) {
}
