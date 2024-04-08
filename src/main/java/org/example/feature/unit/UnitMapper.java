package org.example.feature.unit;

import lombok.RequiredArgsConstructor;
import org.example.entity.AbstractBaseEntity;
import org.example.feature.geo.point.PointMapper;
import org.example.feature.image.ImageMapper;
import org.example.feature.unit.dto.UnitDto;
import org.example.feature.unit.dto.create.UnitCreateRequestDto;
import org.example.feature.user.UserMapper;
import org.example.mapper.IMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitMapper implements IMapper<Unit, UnitDto> {

    private final PointMapper pointMapper;
    private final UserMapper userMapper;
    private final ImageMapper imageMapper;

    @Override
    public Unit fromDtoToObject(UnitDto dto) {
        // Ми не хочемо робити зміни з фронту по полям, що ініціалізовані як new ArrayList<>, null
        return new Unit(
                dto.uuid(),
                dto.name(),
                pointMapper.fromDtoToObject(dto.location()),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                imageMapper.fromDtoToObject(dto.image()),
                dto.description()
        );
    }

    @Override
    public UnitDto fromObjectToDto(Unit object) {
        return new UnitDto(
                object.getId(),
                object.getCreatedAt(),
                object.getUpdatedAt(),
                object.getName(),
                object.getDescription(),
                pointMapper.fromObjectToDto(object.getLocation()),
                object.getOrders().stream().map(AbstractBaseEntity::getId).toList(),
                object.getOrdersHistory().stream().map(AbstractBaseEntity::getId).toList(),
                userMapper.fromObjectListToDtoList(object.getUsers()),
                imageMapper.fromObjectToDto(object.getImage())
        );
    }

    @Override
    public List<Unit> fromDtoListToObjectList(List<UnitDto> dtoList) {
        return dtoList.stream().map(this::fromDtoToObject).toList();
    }

    @Override
    public List<UnitDto> fromObjectListToDtoList(List<Unit> objectList) {
        return objectList.stream().map(this::fromObjectToDto).toList();
    }

    public Unit fromCreateRequestDtoToObject(UnitCreateRequestDto unitCreateRequestDto) {
        return new Unit(
                unitCreateRequestDto.name(),
                pointMapper.fromCreateRequestDtoToObject(unitCreateRequestDto.location()),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                imageMapper.fromCreateRequestDtoToObject(unitCreateRequestDto.image()),
                unitCreateRequestDto.description()
        );
    }
}
