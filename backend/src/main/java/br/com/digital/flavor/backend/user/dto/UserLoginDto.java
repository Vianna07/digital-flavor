package br.com.digital.flavor.backend.user.dto;

import br.com.digital.flavor.backend.user.UserType;

import java.util.UUID;

public record UserLoginDto(UUID id, UserType userType, String name, String email) {
}
