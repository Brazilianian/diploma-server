package org.example.feature.unit.dto;

import org.example.feature.geo.point.PointDto;
import org.example.feature.user.dto.UserDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UnitDto(UUID uuid,
                      LocalDateTime createdAt,
                      LocalDateTime updatedAt,
                      String name,
                      PointDto location,
                      List<UUID> ordersId,
                      List<UUID> ordersHistoryId,
                      List<UserDto> users
) {
}
