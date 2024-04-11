package org.example.feature.user;


import lombok.RequiredArgsConstructor;
import org.example.feature.image.ImageMapper;
import org.example.feature.user.dto.UserDto;
import org.example.feature.user.dto.UserCreateRequestDto;
import org.example.feature.user.dto.UserUpdateRequestDto;
import org.example.mapper.IMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMapper implements IMapper<User, UserDto> {

    private final ImageMapper imageMapper;

    @Override
    public User fromDtoToObject(UserDto userDto) {
        return new User.Builder(userDto.firstName(), userDto.lastName(), userDto.email())
                .id(userDto.id())
                .build();
    }

    @Override
    public UserDto fromObjectToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.getImage() == null ? null : imageMapper.fromObjectToDto(user.getImage()),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    @Override
    public List<User> fromDtoListToObjectList(List<UserDto> userDtoList) {
        return userDtoList.stream().map(this::fromDtoToObject).toList();
    }

    @Override
    public List<UserDto> fromObjectListToDtoList(List<User> users) {
        return users.stream().map(this::fromObjectToDto).toList();
    }

    public User fromCreateRequestDtoToObject(UserCreateRequestDto userCreateRequestDto) {
        return new User.Builder(userCreateRequestDto.firstName(), userCreateRequestDto.lastName(), userCreateRequestDto.email())
                .password(userCreateRequestDto.password())
                .build();
    }

    public User fromUpdateRequestDtoToObject(UserUpdateRequestDto userUpdateRequestDto) {
        return new User(
                userUpdateRequestDto.firstName(),
                userUpdateRequestDto.lastName(),
                imageMapper.fromCreateRequestDtoToObject(userUpdateRequestDto.image())
                );
    }
}
