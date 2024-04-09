package org.example.feature.order;

import jakarta.persistence.*;
import lombok.*;
import org.example.entity.AbstractBaseEntity;
import org.example.feature.orderItem.OrderItem;
import org.example.feature.order_details.OrderDetails;
import org.example.feature.unit.Unit;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends AbstractBaseEntity {
    // Замовник
    @ManyToOne
    private Unit unit;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

    @OneToOne(cascade = CascadeType.ALL)
    private OrderDetails orderDetails;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    public Order(Unit unit, OrderDetails orderDetails) {
        this.unit = unit;
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + getId() +
                "name=" + getName() +
                "unitId=" + unit.getId() +
                ", orderStatus=" + orderStatus +
                ", orderDetails=" + orderDetails +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                '}';
    }
}
