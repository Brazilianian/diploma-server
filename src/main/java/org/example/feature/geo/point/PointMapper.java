package org.example.feature.geo.point;

import org.example.feature.geo.point.dto.PointCreateRequestDto;
import org.example.mapper.IMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointMapper implements IMapper<Point, PointDto> {
    @Override
    public Point fromDtoToObject(PointDto dto) {
        return new Point(
                dto.latitude(),
                dto.longitude()
        );
    }

    @Override
    public PointDto fromObjectToDto(Point object) {
        return new PointDto(
                object.getId(),
                object.getCreatedAt(),
                object.getUpdatedAt(),
                object.getLatitude(),
                object.getLongitude()
        );
    }

    @Override
    public List<Point> fromDtoListToObjectList(List<PointDto> dtoList) {
        return dtoList.stream().map(this::fromDtoToObject).toList();
    }

    @Override
    public List<PointDto> fromObjectListToDtoList(List<Point> objectList) {
        return objectList.stream().map(this::fromObjectToDto).toList();
    }

    public Point fromCreateRequestDtoToObject(PointCreateRequestDto pointCreateRequestDto) {
        return new Point(
                pointCreateRequestDto.latitude(),
                pointCreateRequestDto.longitude()
        );
    }
}
