package org.example.feature.orderItem;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.AbstractBaseEntity;

@Entity
@Table
@NoArgsConstructor
@Data
public class OrderItem extends AbstractBaseEntity {
    private String name;
    private String description;

    public OrderItem(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
