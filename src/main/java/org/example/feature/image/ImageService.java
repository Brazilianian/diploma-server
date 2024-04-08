package org.example.feature.image;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final static String IMAGE_PATH = "src/main/resources/data/";

    private final static Logger LOGGER = LoggerFactory.getLogger(ImageService.class);

    public Image createImage(String image) {
        Image savedImage = imageRepository.save(new Image());
        createImageFile(image, savedImage.getId());
        LOGGER.info(
                String.format("Image was saved. %s", savedImage.getId())
        );
        return savedImage;
    }

    public void createImageFile(String content, UUID id) {
        try (FileOutputStream fos = new FileOutputStream(getFileName(id))) {
            byte[] decodedImg = Base64.getDecoder()
                    .decode(content.split(",")[1].getBytes(StandardCharsets.UTF_8));
            fos.write(decodedImg);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public String getImageContent(UUID id) throws IOException {
        byte[] encodedBytes = Files.readAllBytes(Paths.get(getFileName(id)));
        return "data:image/png;base64," + new String(Base64.getEncoder().encode(encodedBytes));
    }

    private String getFileName(UUID id) {
        return IMAGE_PATH + id + ".png";
    }
}
