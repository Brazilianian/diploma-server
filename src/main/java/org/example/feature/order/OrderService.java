package org.example.feature.order;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.example.feature.order.exception.OrderWasNotFoundException;
import org.example.feature.unit.Unit;
import org.example.feature.unit.UnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final UnitService unitService;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderWasNotFoundException(
                        String.format(
                                "Order with id '%s' was not found", id
                        )
                ));
    }

    public Order createOrder(Order order) {
        Unit unit = unitService.getUnitById(order.getUnit().getId());

        order.setUnit(unit);
        order.setOrderStatus(OrderStatus.PENDING);

        Order savedOrder = orderRepository.save(order);

        LOGGER.info(
                String.format("Order %s was successfully created", order)
        );

        unit.getOrders().add(savedOrder);
        unitService.updateUnit(unit);

        return savedOrder;
    }

    public void deleteOrderById(UUID id) {
        if (!isOrderExistsById(id)) {
            throw new OrderWasNotFoundException(
                    String.format(
                            "Failed to delete order. Order with id '%s' was not found", id
                    ));
        }

        orderRepository.deleteById(id);
    }

    private boolean isOrderExistsById(UUID id) {
        return orderRepository.existsById(id);
    }
}
