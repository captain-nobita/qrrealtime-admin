package com.napas.qr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.AuthenticationException;

import java.util.UUID;

public class ExpiredCredentialException extends AuthenticationException {
    @Getter
    private final UserInfo user;
    public ExpiredCredentialException(UUID userId, String username) {
        super("Credential of " + username + " expired");
        this.user = new UserInfo(userId, username);
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfo {
        private UUID id;
        private String username;
    }
}
