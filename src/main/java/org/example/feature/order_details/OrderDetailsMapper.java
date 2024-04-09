package org.example.feature.order_details;

import lombok.RequiredArgsConstructor;
import org.example.feature.geo.point.PointMapper;
import org.example.feature.order_details.dto.OrderDetailsCreateRequestDto;
import org.example.feature.order_details.dto.OrderDetailsDto;
import org.example.mapper.IMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailsMapper implements IMapper<OrderDetails, OrderDetailsDto> {

    private final PointMapper pointMapper;

    @Override
    public OrderDetails fromDtoToObject(OrderDetailsDto dto) {
        return new OrderDetails(
                pointMapper.fromDtoListToObjectList(dto.points()),
                dto.dateTimeFrom(),
                dto.dateTimeTo(),
                dto.distance(),
                dto.duration()
        );
    }

    @Override
    public OrderDetailsDto fromObjectToDto(OrderDetails object) {
        return new OrderDetailsDto(
                object.getId(),
                object.getCreatedAt(),
                object.getUpdatedAt(),
                pointMapper.fromObjectListToDtoList(object.getPoints()),
                object.getDateTimeTo(),
                object.getDateTimeTo(),
                object.getDistance(),
                object.getDuration()
        );
    }

    @Override
    public List<OrderDetails> fromDtoListToObjectList(List<OrderDetailsDto> dtoList) {
        return dtoList.stream().map(this::fromDtoToObject).toList();
    }

    @Override
    public List<OrderDetailsDto> fromObjectListToDtoList(List<OrderDetails> objectList) {
        return objectList.stream().map(this::fromObjectToDto).toList();
    }

    public OrderDetails fromCreateRequestDtoToObject(OrderDetailsCreateRequestDto orderDetailsCreateRequestDto) {
        return new OrderDetails(
                orderDetailsCreateRequestDto.points().stream().map(pointMapper::fromCreateRequestDtoToObject).toList(),
                LocalDate.parse(orderDetailsCreateRequestDto.dateTimeFrom()),
                LocalDate.parse(orderDetailsCreateRequestDto.dateTimeTo()),
                orderDetailsCreateRequestDto.distance(),
                orderDetailsCreateRequestDto.duration()
        );
    }
}
