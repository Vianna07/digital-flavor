package br.com.digital.flavor.backend.user;

import java.util.UUID;

public record UserDto(UUID id, UserType userType, String name, String email) {}
