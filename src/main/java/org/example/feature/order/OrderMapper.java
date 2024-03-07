package org.example.feature.order;

import lombok.RequiredArgsConstructor;
import org.example.feature.order.dto.OrderCreateRequestDto;
import org.example.feature.order.dto.OrderDto;
import org.example.feature.order_details.OrderDetailsMapper;
import org.example.feature.unit.Unit;
import org.example.mapper.IMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderMapper implements IMapper<Order, OrderDto> {

    private final OrderDetailsMapper orderDetailsMapper;

    @Override

    public Order fromDtoToObject(OrderDto dto) {
        // ми не будемо змінювати unit, тому він null
        return new Order(
                null,
                dto.orderStatus(),
                orderDetailsMapper.fromDtoToObject(dto.orderDetailsDto())
        );
    }

    @Override
    public OrderDto fromObjectToDto(Order object) {
        return new OrderDto(
                object.getId(),
                object.getUnit().getId(),
                object.getCreatedAt(),
                object.getUpdatedAt(),
                object.getOrderStatus(),
                orderDetailsMapper.fromObjectToDto(object.getOrderDetails())
        );
    }

    @Override
    public List<Order> fromDtoListToObjectList(List<OrderDto> dtoList) {
        return dtoList.stream().map(this::fromDtoToObject).toList();
    }

    @Override
    public List<OrderDto> fromObjectListToDtoList(List<Order> objectList) {
        return objectList.stream().map(this::fromObjectToDto).toList();
    }

    public Order fromCreateRequestDtoToObject(OrderCreateRequestDto orderCreateRequestDto) {
        return new Order(
                new Unit(orderCreateRequestDto.unitId()),
                orderDetailsMapper.fromCreateRequestDtoToObject(orderCreateRequestDto.orderDetailsCreateRequestDto())
        );
    }
}
