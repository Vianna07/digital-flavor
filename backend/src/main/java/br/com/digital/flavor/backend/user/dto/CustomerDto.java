package br.com.digital.flavor.backend.user.dto;

import java.util.UUID;

public record CustomerDto(UUID id, String name, String email) {
}
