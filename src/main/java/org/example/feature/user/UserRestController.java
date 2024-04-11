package org.example.feature.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.exception.ValidationException;
import org.example.feature.user.dto.UserDto;
import org.example.feature.user.dto.UserUpdateRequestDto;
import org.example.feature.user.dto.UserValidateDto;
import org.example.service.UserService;
import org.example.util.ValidationUtil;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/profile")
    public UserDto getUserProfile(Principal userPrincipal) {
        User user = userService.getUserFromUserPrincipal(userPrincipal);
        return userMapper.fromObjectToDto(user);
    }

    @GetMapping("/validate")
    public UserValidateDto getUserValidateDto(Principal userPrincipal) {
        User user = userService.getUserFromUserPrincipal(userPrincipal);
        return new UserValidateDto(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }

    @PutMapping
    public UserDto updateUser(@RequestBody @Valid UserUpdateRequestDto userUpdateRequestDto, Principal userPrincipal,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ValidationUtil.getErrors(bindingResult);
            throw new ValidationException("Помилка оновлення інформації про користувача", errors);
        }

        User user = userService.getUserFromUserPrincipal(userPrincipal);
        User userInfoToUpdate = userMapper.fromUpdateRequestDtoToObject(userUpdateRequestDto);

        User updatedUser = userService.updateUser(user, userInfoToUpdate, userUpdateRequestDto.image().content());
        return userMapper.fromObjectToDto(updatedUser);
    }
}
