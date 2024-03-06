package org.example.feature.order_details;

import org.example.mapper.IMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsMapper implements IMapper<OrderDetails, OrderDetailsDto> {
    @Override
    public OrderDetails fromDtoToObject(OrderDetailsDto dto) {
        return null;
    }

    @Override
    public OrderDetailsDto fromObjectToDto(OrderDetails object) {
        return null;
    }

    @Override
    public List<OrderDetails> fromDtoListToObjectList(List<OrderDetailsDto> dtoList) {
        return null;
    }

    @Override
    public List<OrderDetailsDto> fromObjectListToDtoList(List<OrderDetails> objectList) {
        return null;
    }
}
