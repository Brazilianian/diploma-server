package org.example.feature.geo.point;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(PointService.class);

    public Point createPoint(Point point) {
        Point createdPoint = pointRepository.save(point);
        LOGGER.info(String.format(
                "Point %s was successfully created", point
        ));
        return createdPoint;
    }
}
