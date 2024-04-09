package org.example.feature.orderItem;

import org.example.feature.orderItem.dto.OrderItemDto;
import org.example.mapper.IMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemMapper implements IMapper<OrderItem, OrderItemDto> {
    @Override
    public OrderItem fromDtoToObject(OrderItemDto dto) {
        return new OrderItem(
                dto.name(),
                dto.description()
        );
    }

    @Override
    public OrderItemDto fromObjectToDto(OrderItem object) {
        return new OrderItemDto(
                object.getId(),
                object.getName(),
                object.getDescription(),
                object.getCreatedAt(),
                object.getUpdatedAt()
                );
    }

    @Override
    public List<OrderItem> fromDtoListToObjectList(List<OrderItemDto> dtoList) {
        return dtoList.stream().map(this::fromDtoToObject).toList();
    }

    @Override
    public List<OrderItemDto> fromObjectListToDtoList(List<OrderItem> objectList) {
        return objectList.stream().map(this::fromObjectToDto).toList();
    }
}
