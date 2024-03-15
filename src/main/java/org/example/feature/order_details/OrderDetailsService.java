package org.example.feature.order_details;

import lombok.RequiredArgsConstructor;
import org.example.feature.geo.point.PointService;
import org.example.feature.order.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final PointService pointService;

    private final Logger LOGGER = LoggerFactory.getLogger(OrderDetailsService.class);

    public OrderDetails createOrderDetails(OrderDetails orderDetails) {

        orderDetails.setPointFrom(pointService.createPoint(orderDetails.getPointFrom()));
        orderDetails.setPointTo(pointService.createPoint(orderDetails.getPointTo()));

        OrderDetails createdOrderDetails = orderDetailsRepository.save(orderDetails);
        LOGGER.info(
                String.format(
                        "Деталі замовлення %s успішно створено", createdOrderDetails
                )
        );
        return createdOrderDetails;
    }
}
