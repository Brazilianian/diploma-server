package org.example.feature.unit;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.example.entity.AbstractBaseEntity;
import org.example.feature.user.User;
import org.example.feature.geo.Point;
import org.example.feature.order.Order;

import java.util.ArrayList;
import java.util.List;

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
    private Point point;

    // поточні замовлення
    @OneToMany
    private List<Order> orders = new ArrayList<>();

    // історія замовлень
    @OneToMany
    private List<Order> ordersHistory = new ArrayList<>();

    // користувачі підрозділу
    @OneToMany
    private List<User> users = new ArrayList<>();
}
