package org.example.feature.order_details;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.example.entity.AbstractBaseEntity;
import org.example.feature.geo.point.Point;
import org.example.feature.order.Order;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_details")
public class OrderDetails extends AbstractBaseEntity {
    // Звідки
    @OneToOne(cascade = CascadeType.ALL)
    private Point pointFrom;

    // Куди
    @OneToOne(cascade = CascadeType.ALL)
    private Point pointTo;

    // До коли діставити
    private LocalDateTime dateTimeTo;

    private double distance;

    public OrderDetails(Point pointFrom, Point pointTo, LocalDateTime dateTimeTo) {
        this.pointFrom = pointFrom;
        this.pointTo = pointTo;
        this.dateTimeTo = dateTimeTo;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + getId() +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                ", pointFrom=" + pointFrom +
                ", pointTo=" + pointTo +
                ", dateTimeTo=" + dateTimeTo +
                ", distance=" + distance +
                '}';
    }
}
