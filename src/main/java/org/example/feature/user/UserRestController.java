package org.example.feature.user;

import lombok.RequiredArgsConstructor;
import org.example.feature.user.dto.UserDto;
import org.example.feature.user.dto.UserValidateDto;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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
}
