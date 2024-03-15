package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.feature.auth.dto.AuthRequestDto;
import org.example.feature.auth.dto.AuthResponseDto;
import org.example.feature.user.User;
import org.springframework.http.HttpHeaders;
import org.example.feature.user.UserRepository;
import org.example.feature.user.exception.UserWasNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDto authenticate(AuthRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                ));

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UserWasNotFoundException(
                        String.format("Не вдалося автентифікуватися. Користувач з електронною поштою %s не знайдений.", request.email())
                ));
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        tokenService.revokeAllUserTokens(user);
        tokenService.saveUserToken(user, jwtToken);

        return new AuthResponseDto(jwtToken, refreshToken);
    }

    public void refresh(HttpServletRequest request, HttpServletResponse response)  throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new UserWasNotFoundException(
                            String.format("Не вдалося оновити токен. Користувач з електронною поштою %s не знайдений.", userEmail)));
            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateToken(user);
                tokenService.revokeAllUserTokens(user);
                tokenService.saveUserToken(user, accessToken);
                AuthResponseDto authResponse = new AuthResponseDto(accessToken, refreshToken);
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
