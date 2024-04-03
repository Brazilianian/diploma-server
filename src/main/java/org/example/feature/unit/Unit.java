package org.example.feature.unit;

import jakarta.persistence.*;
import lombok.*;
import org.example.entity.AbstractBaseEntity;
import org.example.feature.image.Image;
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

    @OneToOne(cascade = CascadeType.ALL)
    private Point location;

    // поточні замовлення
    @OneToMany
    private List<Order> orders = new ArrayList<>();

    // історія замовлень
    @OneToMany
    private List<Order> ordersHistory = new ArrayList<>();

    // користувачі підрозділу
    @ManyToMany
    private List<User> users = new ArrayList<>();

    @OneToOne
    private Image image;

    public Unit(UUID id) {
        super(id);
    }

    public Unit(UUID id, String name, Point location, List<Order> orders, List<Order> ordersHistory, List<User> users, Image image) {
        super(id);
        this.name = name;
        this.location = location;
        this.orders = orders;
        this.ordersHistory = ordersHistory;
        this.users = users;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                "image='" + image + '\'' +
                ", location=" + location +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                '}';
    }
}
