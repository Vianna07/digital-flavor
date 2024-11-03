package br.com.digital.flavor.backend.user.dto;

import br.com.digital.flavor.backend.user.UserType;

public record NewUserDto(String name, String email, String password, UserType userType) {
}
