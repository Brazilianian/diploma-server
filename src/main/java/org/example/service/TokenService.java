package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.feature.user.User;
import org.example.feature.auth.token.Token;
import org.example.feature.auth.token.TokenRepository;
import org.example.feature.auth.token.TokenType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public void saveUserToken(User user, String jwtToken) {
        Token token = new Token.Builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .isExpired(false)
                .isRevoked(false)
                .build();
        tokenRepository.save(token);
    }

    public void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findByIsRevokedAndIsExpiredAndUser_Id(false, false, user.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }

        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }
}