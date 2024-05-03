package org.example.feature.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.feature.auth.dto.AuthRequestDto;
import org.example.feature.auth.dto.AuthResponseDto;
import org.example.exception.ValidationException;
import org.example.feature.user.dto.UserDto;
import org.example.feature.user.dto.UserCreateRequestDto;
import org.example.feature.user.User;
import org.example.feature.user.UserMapper;
import org.example.service.AuthService;
import org.example.feature.user.UserService;
import org.example.util.ValidationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthRestController {

    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@RequestBody @Valid UserCreateRequestDto userCreateRequestDto,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ValidationUtil.getErrors(bindingResult);
            throw new ValidationException("\n" +
                    "Не вдалося зареєструвати нового користувача.", errors);
        }

        User user = userMapper.fromCreateRequestDtoToObject(userCreateRequestDto);
        return userMapper.fromObjectToDto(userService.createUser(user));
    }

    @PostMapping("/authenticate")
    public AuthResponseDto authenticate(@RequestBody AuthRequestDto request) {
        return authService.authenticate(request);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authService.refresh(request, response);
    }
}