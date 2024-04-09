package org.example.feature.order_details;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entity.AbstractBaseEntity;
import org.example.feature.geo.point.Point;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetails extends AbstractBaseEntity {
    @OneToMany(cascade = CascadeType.ALL)
    private List<Point> points = new ArrayList<>();

    // До коли діставити
    private LocalDate dateTimeFrom;

    // До коли діставити
    private LocalDate dateTimeTo;

    private String distance;
    private String duration;

    public OrderDetails(List<Point> points, LocalDate dateTimeFrom, LocalDate dateTimeTo, String distance, String duration) {
        this.points = points;
        this.dateTimeFrom = dateTimeFrom;
        this.dateTimeTo = dateTimeTo;
        this.distance = distance;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + getId() +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                ", pointFrom=" + points +
                ", pointTo=" + points +
                ", dateTimeTo=" + dateTimeTo +
                ", distance=" + distance +
                '}';
    }
}
