package org.example.feature.order_details;

import lombok.RequiredArgsConstructor;
import org.example.feature.geo.point.Point;
import org.example.feature.geo.point.PointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final PointService pointService;

    private final Logger LOGGER = LoggerFactory.getLogger(OrderDetailsService.class);

    public OrderDetails createOrderDetails(OrderDetails orderDetails) {

        List<Point> savedPoints = new ArrayList<>();

        for (Point point : orderDetails.getPoints()) {
            savedPoints.add(pointService.createPoint(point));
        }

        orderDetails.setPoints(savedPoints);

        OrderDetails createdOrderDetails = orderDetailsRepository.save(orderDetails);
        LOGGER.info(
                String.format(
                        "Деталі замовлення %s успішно створено", createdOrderDetails
                )
        );
        return createdOrderDetails;
    }
}
