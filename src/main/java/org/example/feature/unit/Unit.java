package org.example.feature.unit;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.example.entity.AbstractBaseEntity;
import org.example.feature.user.User;
import org.example.feature.geo.point.Point;
import org.example.feature.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "units")
public class Unit extends AbstractBaseEntity {
    private String name;

    @OneToOne
    private Point location;

    // поточні замовлення
    @OneToMany
    private List<Order> orders = new ArrayList<>();

    // історія замовлень
    @OneToMany
    private List<Order> ordersHistory = new ArrayList<>();

    // користувачі підрозділу
    @OneToMany
    private List<User> users = new ArrayList<>();

    public Unit(UUID id, String name, Point location, List<Order> orders, List<Order> ordersHistory, List<User> users) {
        super(id);
        this.name = name;
        this.location = location;
        this.orders = orders;
        this.ordersHistory = ordersHistory;
        this.users = users;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                ", location=" + location +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                '}';
    }
}
