package org.example.feature.geo.point;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.example.entity.AbstractBaseEntity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "points")
public class Point extends AbstractBaseEntity {
    private double latitude;
    private double longitude;
}
