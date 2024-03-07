package org.example.feature.order_details;

import lombok.RequiredArgsConstructor;
import org.example.feature.geo.point.PointMapper;
import org.example.feature.order_details.dto.OrderDetailsCreateRequestDto;
import org.example.feature.order_details.dto.OrderDetailsDto;
import org.example.mapper.IMapper;
import org.springframework.security.config.annotation.web.CorsDsl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailsMapper implements IMapper<OrderDetails, OrderDetailsDto> {

    private final PointMapper pointMapper;

    @Override
    public OrderDetails fromDtoToObject(OrderDetailsDto dto) {
        return new OrderDetails(
                pointMapper.fromDtoToObject(dto.pointFrom()),
                pointMapper.fromDtoToObject(dto.pointTo()),
                dto.dateTimeTo()
        );
    }

    @Override
    public OrderDetailsDto fromObjectToDto(OrderDetails object) {
        return new OrderDetailsDto(
                object.getId(),
                object.getCreatedAt(),
                object.getUpdatedAt(),
                pointMapper.fromObjectToDto(object.getPointFrom()),
                pointMapper.fromObjectToDto(object.getPointTo()),
                object.getDateTimeTo(),
                object.getDistance()
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
                pointMapper.fromCreateRequestDtoToObject(orderDetailsCreateRequestDto.pointFrom()),
                pointMapper.fromCreateRequestDtoToObject(orderDetailsCreateRequestDto.pointTo()),
                orderDetailsCreateRequestDto.dateTimeTo()
        );
    }
}
