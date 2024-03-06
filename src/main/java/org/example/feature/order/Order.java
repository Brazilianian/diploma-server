package org.example.feature.order;

import jakarta.persistence.*;
import lombok.*;
import org.example.entity.AbstractBaseEntity;
import org.example.feature.order_details.OrderDetails;
import org.example.feature.unit.Unit;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends AbstractBaseEntity {
    // Замовник
    @OneToOne
    private Unit unit;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

    @OneToOne
    private OrderDetails orderDetails;
}
