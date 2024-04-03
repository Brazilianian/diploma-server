package org.example.feature.image;

import org.example.feature.image.dto.ImageDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, ImageDto> {
}
