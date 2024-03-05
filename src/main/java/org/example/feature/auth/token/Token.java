package org.example.feature.auth.token;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.AbstractBaseEntity;
import org.example.feature.user.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tokens")
public class Token extends AbstractBaseEntity {
    @Column(name = "token", unique = true)
    private String token;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tyken_type")
    private TokenType tokenType;

    @Column(name = "is_revoked")
    private boolean isRevoked;

    @Column(name = "is_expired")
    private boolean isExpired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;

    public static class Builder {

        private String token;
        private TokenType tokenType;
        private boolean isRevoked;
        private boolean isExpired;
        private User user;

        public Builder() {
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder tokenType(TokenType tokenType) {
            this.tokenType = tokenType;
            return this;
        }

        public Builder isRevoked(boolean isRevoked) {
            this.isRevoked = isRevoked;
            return this;
        }

        public Builder isExpired(boolean isExpired) {
            this.isExpired = isExpired;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Token build() {
            return new Token(token, tokenType, isRevoked, isExpired, user);
        }
    }
}