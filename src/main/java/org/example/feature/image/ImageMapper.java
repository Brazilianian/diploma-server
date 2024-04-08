package org.example.feature.image;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.feature.image.dto.ImageCreateRequestDto;
import org.example.feature.image.dto.ImageDto;
import org.example.mapper.IMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageMapper implements IMapper<Image, ImageDto> {

    private final ImageService imageService;
    private final static Logger LOGGER = LoggerFactory.getLogger(ImageMapper.class);

    @Override
    public Image fromDtoToObject(ImageDto dto) {
        return new Image();
    }

    @Override
    public ImageDto fromObjectToDto(Image object) {
        String content = null;
        try {
            content = imageService.getImageContent(object.getId());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        return new ImageDto(
                object.getId(),
                content,
                object.getCreatedAt(),
                object.getUpdatedAt()
        );
    }

    @Override
    public List<Image> fromDtoListToObjectList(List<ImageDto> dtoList) {
        return dtoList.stream().map(this::fromDtoToObject).toList();
    }

    @Override
    public List<ImageDto> fromObjectListToDtoList(List<Image> objectList) {
        return objectList.stream().map(this::fromObjectToDto).toList();
    }

    public Image fromCreateRequestDtoToObject(ImageCreateRequestDto image) {
        return new Image();
    }
}
