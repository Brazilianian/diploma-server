package org.example.feature.order;

import lombok.RequiredArgsConstructor;
import org.example.feature.order.exception.OrderWasNotFoundException;
import org.example.feature.order_details.OrderDetailsService;
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

    private final OrderRepository orderRepository;
    private final UnitService unitService;
    private final OrderDetailsService orderDetailsService;

    private final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    public List<Order> getAllOrders() {
        return orderRepository.findAll()
                .stream().filter(os -> !os.getOrderStatus().equals(OrderStatus.CANCELED))
                .toList();
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
        order.setOrderDetails(orderDetailsService.createOrderDetails(order.getOrderDetails()));

        Order savedOrder = orderRepository.save(order);

        LOGGER.info(
                String.format("Order %s was successfully created", order)
        );

        unit.getOrders().add(savedOrder);
        unitService.updateUnit(unit);

        return savedOrder;
    }

    public void deleteOrderById(UUID id) {
        Order order = getOrderById(id);

        Unit unit = unitService.getUnitById(order.getUnit().getId());
        unit.getOrders().remove(order);
        unit.getOrdersHistory().add(order);
        unitService.updateUnit(unit);

        order.setOrderStatus(OrderStatus.CANCELED);
        orderRepository.save(order);
        LOGGER.info(String.format("Order status for order '%s' was changed to CANCELED", id));
    }

    private boolean isOrderExistsById(UUID id) {
        return orderRepository.existsById(id);
    }
}
