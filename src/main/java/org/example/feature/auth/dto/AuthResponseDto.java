package org.example.feature.auth.dto;

import org.example.feature.user.dto.UserDto;

public record AuthResponseDto(
        UserDto user,
        TokensDto tokens
) {
}
