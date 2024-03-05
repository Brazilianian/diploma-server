package org.example.dto.auth.request;

public record AuthRequestDto(
        String email,
        String password
) {
}
