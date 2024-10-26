package br.com.digital.flavor.backend.security.dto;

import br.com.digital.flavor.backend.user.User;

public record LoginRequest(String email, String password, String canteenId) {
    public LoginRequest(User user) {
        this(user.getEmail(), user.getPassword(), null);
    }
}
