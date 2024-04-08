package org.example.feature.image;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entity.AbstractBaseEntity;

import java.util.UUID;

@Entity
@Table(name = "images")
@NoArgsConstructor
@Getter
@Setter
public class Image extends AbstractBaseEntity {

    public Image(UUID id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Image{id: " + getId() + "}";
    }
}