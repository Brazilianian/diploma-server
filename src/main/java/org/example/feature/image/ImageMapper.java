package org.example.feature.image;

import org.example.feature.image.dto.ImageCreateRequestDto;
import org.example.feature.image.dto.ImageDto;
import org.example.mapper.IMapper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service
public class ImageMapper implements IMapper<Image, ImageDto> {
    @Override
    public Image fromDtoToObject(ImageDto dto) {
        return new Image(dto.content().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public ImageDto fromObjectToDto(Image object) {
        return new ImageDto(
                object.getId(),
                Arrays.toString(object.getContent()),
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
        return new Image(image.content().getBytes(StandardCharsets.UTF_8));
    }
}
