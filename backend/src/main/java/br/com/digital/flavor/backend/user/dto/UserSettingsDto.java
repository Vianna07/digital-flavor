package br.com.digital.flavor.backend.user.dto;

import br.com.digital.flavor.backend.user.User;

public record UserSettingsDto(String name, String email) {
    public UserSettingsDto(User user) {
        this(user.getName(), user.getEmail());
    }
}
