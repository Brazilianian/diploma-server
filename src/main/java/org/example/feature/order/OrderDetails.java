package org.example.feature.order;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.example.entity.AbstractBaseEntity;
import org.example.feature.geo.Point;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_details")
public class OrderDetails extends AbstractBaseEntity {
    @OneToOne
    private Order order;

    // Звідки
    @OneToOne
    private Point pointFrom;

    // Куди
    @OneToOne
    private Point pointTo;

    // До коли діставити
    private LocalDateTime dateTimeTo;

    private double distance;

}
