package org.example.feature.image;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LoggerGroup;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    private final static Logger LOGGER = LoggerFactory.getLogger(ImageService.class);

    public Image createImage(Image image) {
        Image savedImage = imageRepository.save(image);
        LOGGER.info(
                String.format("Image was saved. %s", image)
        );
        return savedImage;
    }
}
