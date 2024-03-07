package org.example.feature.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.exception.ValidationException;
import org.example.feature.order.dto.OrderCreateRequestDto;
import org.example.feature.order.dto.OrderDto;
import org.example.util.ValidationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderRestController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping
    @ResponseBody
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return orderMapper.fromObjectListToDtoList(orders);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable("id") String id) {
        Order order = orderService.getOrderById(UUID.fromString(id));
        return orderMapper.fromObjectToDto(order);
    }

    @ResponseBody
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody @Valid OrderCreateRequestDto orderCreateRequestDto,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ValidationUtil.getErrors(bindingResult);
            throw new ValidationException("Failed to create new order", errors);
        }

        Order order = orderMapper.fromCreateRequestDtoToObject(orderCreateRequestDto);
        order = orderService.createOrder(order);
        return orderMapper.fromObjectToDto(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderById(@PathVariable("id") String id) {
        orderService.deleteOrderById(UUID.fromString(id));
    }
}
